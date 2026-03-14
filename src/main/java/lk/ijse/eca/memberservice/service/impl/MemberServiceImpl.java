package lk.ijse.eca.memberservice.service.impl;

import lk.ijse.eca.memberservice.dto.MemberDTO;
import lk.ijse.eca.memberservice.entity.Member;
import lk.ijse.eca.memberservice.exception.ResourceNotFoundException;
import lk.ijse.eca.memberservice.mapper.MemberMapper;
import lk.ijse.eca.memberservice.repository.MemberRepository;
import lk.ijse.eca.memberservice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

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
        memberRepository.delete(member);
    }
}
