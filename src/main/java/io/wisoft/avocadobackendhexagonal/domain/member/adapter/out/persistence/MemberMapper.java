package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public static MemberEntity memberToMemberEntity(final Member member) {
        return new MemberEntity(
                member.getId() == null? null : member.getId(),
                member.getEmail(),
                member.getPassword()
        );
    }

    public static Member memberEntityToMember(final MemberEntity memberEntity) {
        return new Member(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getPassword()
        );
    }
}
