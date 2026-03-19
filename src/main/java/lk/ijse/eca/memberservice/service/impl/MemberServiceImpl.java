package lk.ijse.eca.memberservice.service.impl;

import lk.ijse.eca.memberservice.dto.MemberDTO;
import lk.ijse.eca.memberservice.entity.Member;
import lk.ijse.eca.memberservice.client.FileServiceClient;
import lk.ijse.eca.memberservice.exception.ResourceNotFoundException;
import lk.ijse.eca.memberservice.mapper.MemberMapper;
import lk.ijse.eca.memberservice.repository.MemberRepository;
import lk.ijse.eca.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final FileServiceClient fileServiceClient;

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = memberMapper.toEntity(memberDTO);
        member.setJoinedAt(LocalDateTime.now());
        Member savedMember = memberRepository.save(member);
        return memberMapper.toDTO(savedMember);
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        return memberMapper.toDTOList(memberRepository.findAll());
    }

    @Override
    public MemberDTO getMemberById(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return memberMapper.toDTO(member);
    }

    @Override
    public MemberDTO updateMember(String id, MemberDTO memberDTO) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        existingMember.setName(memberDTO.getName());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setPhone(memberDTO.getPhone());
        
        Member updatedMember = memberRepository.save(existingMember);
        return memberMapper.toDTO(updatedMember);
    }

    @Override
    public void deleteMember(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        
        // Try to delete image if exists
        if (member.getImageUrl() != null && !member.getImageUrl().isEmpty()) {
            try {
                String filename = member.getImageUrl().substring(member.getImageUrl().lastIndexOf("/") + 1);
                fileServiceClient.deleteFile(filename);
            } catch (Exception ignored) {}
        }
        
        memberRepository.delete(member);
    }

    @Override
    public MemberDTO uploadImage(String id, MultipartFile file) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        ResponseEntity<Map<String, Object>> response = fileServiceClient.uploadFile(file);
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String publicUrl = (String) response.getBody().get("publicUrl");
            member.setImageUrl(publicUrl);
            Member savedMember = memberRepository.save(member);
            return memberMapper.toDTO(savedMember);
        } else {
            throw new RuntimeException("Failed to upload image to file-service");
        }
    }

    @Override
    public MemberDTO deleteImage(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        if (member.getImageUrl() != null && !member.getImageUrl().isEmpty()) {
            String filename = member.getImageUrl().substring(member.getImageUrl().lastIndexOf("/") + 1);
            fileServiceClient.deleteFile(filename);
            
            member.setImageUrl(null);
            Member savedMember = memberRepository.save(member);
            return memberMapper.toDTO(savedMember);
        }
        
        return memberMapper.toDTO(member);
    }
}
