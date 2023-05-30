package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    /* 연관관계 편의 메서드 */
    public void setMember(final MemberEntity member) {
        //comment: 기존 관계 제거
        if (this.member != null) {
            this.member.getBoardList().remove(this);
        }

        this.member = member;

        //comment: 무한루프 방지
        if (!member.getBoardList().contains(this)) {
            member.getBoardList().add(this);
        }
    }

    public BoardEntity(final Long id, final String title, final String body, final MemberEntity member) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.setMember(member);
    }
}
