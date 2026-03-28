package lk.ijse.eca.memberservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lk.ijse.eca.memberservice.dto.MemberDTO;
import lk.ijse.eca.memberservice.entity.Member;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-25T12:53:21+0530",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setEmail( member.getEmail() );
        memberDTO.setId( member.getId() );
        memberDTO.setImageUrl( member.getImageUrl() );
        memberDTO.setJoinedAt( member.getJoinedAt() );
        memberDTO.setName( member.getName() );
        memberDTO.setPhone( member.getPhone() );

        return memberDTO;
    }

    @Override
    public Member toEntity(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setEmail( memberDTO.getEmail() );
        member.setId( memberDTO.getId() );
        member.setImageUrl( memberDTO.getImageUrl() );
        member.setJoinedAt( memberDTO.getJoinedAt() );
        member.setName( memberDTO.getName() );
        member.setPhone( memberDTO.getPhone() );

        return member;
    }

    @Override
    public List<MemberDTO> toDTOList(List<Member> members) {
        if ( members == null ) {
            return null;
        }

        List<MemberDTO> list = new ArrayList<MemberDTO>( members.size() );
        for ( Member member : members ) {
            list.add( toDTO( member ) );
        }

        return list;
    }
}
