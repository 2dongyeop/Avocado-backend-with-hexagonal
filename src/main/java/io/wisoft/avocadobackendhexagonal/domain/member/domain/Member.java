package io.wisoft.avocadobackendhexagonal.domain.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;
    private String email;
    private String password;

    public Member(final Long id, final String email, final String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static Member withoutId(final String email, final String password) {
        Member member = new Member();
        member.email = email;
        member.password = password;

        return member;
    }
}
