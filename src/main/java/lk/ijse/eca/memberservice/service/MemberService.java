package lk.ijse.eca.memberservice.service;

import lk.ijse.eca.memberservice.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    MemberDTO createMember(MemberDTO memberDTO);
    List<MemberDTO> getAllMembers();
    MemberDTO getMemberById(String id);
    MemberDTO updateMember(String id, MemberDTO memberDTO);
    void deleteMember(String id);
    MemberDTO uploadImage(String id, org.springframework.web.multipart.MultipartFile file);
    MemberDTO deleteImage(String id);
}
