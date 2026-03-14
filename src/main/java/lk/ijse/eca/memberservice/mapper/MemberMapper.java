package lk.ijse.eca.memberservice.mapper;

import lk.ijse.eca.memberservice.dto.MemberDTO;
import lk.ijse.eca.memberservice.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDTO toDTO(Member member);

    Member toEntity(MemberDTO memberDTO);

    List<MemberDTO> toDTOList(List<Member> members);
}
