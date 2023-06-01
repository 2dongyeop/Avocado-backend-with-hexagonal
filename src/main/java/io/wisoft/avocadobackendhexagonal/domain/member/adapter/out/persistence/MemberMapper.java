package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberEntity memberToMemberEntity(final Member member) {
        return MemberEntity.createMemberEntity(
                member.getId() == null? null : member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPassword(),
                member.getPhoneNumber(),
                member.getMemberPhotoPath()
        );
    }

    public static Member memberEntityToMember(final MemberEntity memberEntity) {
        return Member.createMember(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getNickname(),
                memberEntity.getPassword(),
                memberEntity.getPhoneNumber(),
                memberEntity.getMemberPhotoPath()
        );
    }
}
