package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.boardreply.adapter.out.persistence.BoardReplyEntity;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberEntity;
import io.wisoft.avocadobackendhexagonal.global.basetime.BaseTimeEntity;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import io.wisoft.avocadobackendhexagonal.global.enumeration.status.BoardStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "board_title", nullable = false)
    private String title;

    @Column(name = "board_body", nullable = false, columnDefinition = "TEXT")
    private String body;

    @Column(name = "board_photo_path")
    private String boardPhotoPath;

    @Column(name = "board_status")
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Column(name = "dept")
    @Enumerated(EnumType.STRING)
    private HospitalDept dept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "boardEntity")
    private List<BoardReplyEntity> boardReplyEntities = new ArrayList<>();


    /* 연관관계 편의 메서드 */
    public void setMemberEntity(final MemberEntity member) {
        //comment: 기존 관계 제거
        if (this.memberEntity != null) {
            this.memberEntity.getBoards().remove(this);
        }

        this.memberEntity = member;

        //comment: 무한루프 방지
        if (!member.getBoards().contains(this)) {
            member.getBoards().add(this);
        }
    }

    public static BoardEntity createBoardEntity(
            final Long id,
            final String title,
            final String body,
            final String boardPhotoPath,
            final HospitalDept dept,
            final MemberEntity memberEntity) {

        final BoardEntity boardEntity = new BoardEntity();
        boardEntity.id = id;
        boardEntity.title = title;
        boardEntity.body = body;
        boardEntity.boardPhotoPath = boardPhotoPath;
        boardEntity.dept = dept;

        boardEntity.setMemberEntity(memberEntity);
        boardEntity.status = BoardStatus.WRITE;

        return boardEntity;
    }
}
