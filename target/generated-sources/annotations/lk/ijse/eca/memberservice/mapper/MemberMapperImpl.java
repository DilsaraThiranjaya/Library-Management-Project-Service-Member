package lk.ijse.eca.memberservice.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import lk.ijse.eca.memberservice.dto.MemberDTO;
import lk.ijse.eca.memberservice.entity.Member;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-19T12:43:09+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.2 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDTO toDTO(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setId( member.getId() );
        memberDTO.setName( member.getName() );
        memberDTO.setEmail( member.getEmail() );
        memberDTO.setPhone( member.getPhone() );
        memberDTO.setJoinedAt( member.getJoinedAt() );
        memberDTO.setImageUrl( member.getImageUrl() );

        return memberDTO;
    }

    @Override
    public Member toEntity(MemberDTO memberDTO) {
        if ( memberDTO == null ) {
            return null;
        }

        Member member = new Member();

        member.setId( memberDTO.getId() );
        member.setName( memberDTO.getName() );
        member.setEmail( memberDTO.getEmail() );
        member.setPhone( memberDTO.getPhone() );
        member.setJoinedAt( memberDTO.getJoinedAt() );
        member.setImageUrl( memberDTO.getImageUrl() );

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
