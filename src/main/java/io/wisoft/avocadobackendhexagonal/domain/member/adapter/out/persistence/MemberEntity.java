package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_email", unique = true, nullable = false)
    private String email;

    @Column(name = "member_nickname", unique = true, nullable = false)
    private String nickname;

    @Column(name = "member_password", nullable = false)
    private String password;

    @Column(name = "member_phonenumber", nullable = false)
    private String phoneNumber;

    @Column(name = "member_photo_path")
    private String memberPhotoPath;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<BoardEntity> boards = new ArrayList<>();

    public static MemberEntity createMemberEntity(
            final Long id,
            final String email,
            final String nickname,
            final String password,
            final String phoneNumber,
            final String memberPhotoPath) {

        final MemberEntity memberEntity = new MemberEntity();
        memberEntity.id = id;
        memberEntity.email = email;
        memberEntity.nickname = nickname;
        memberEntity.password = password;
        memberEntity.phoneNumber = phoneNumber;
        memberEntity.memberPhotoPath = memberPhotoPath;

        return memberEntity;
    }
}
