package io.wisoft.avocadobackendhexagonal.domain.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String phoneNumber;
    private String memberPhotoPath;

    public static Member createMember(
            final Long id,
            final String email,
            final String nickname,
            final String password,
            final String phoneNumber,
            final String memberPhotoPath) {

        final Member member = new Member();
        member.id = id;
        member.email = email;
        member.nickname = nickname;
        member.password = password;
        member.phoneNumber = phoneNumber;
        member.memberPhotoPath = memberPhotoPath;

        return member;
    }

    public void update(final String email) {
        this.email = email;
    }
}
