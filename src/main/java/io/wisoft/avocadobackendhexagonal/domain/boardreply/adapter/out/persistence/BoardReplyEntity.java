package io.wisoft.avocadobackendhexagonal.domain.boardreply.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardEntity;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id")
    private StaffEntity staffEntity;

    public void setBoardEntity(final BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
        boardEntity.getBoardReplyEntities().add(this);
    }

    public void setStaffEntity(final StaffEntity staffEntity) {
        this.staffEntity = staffEntity;
        staffEntity.getBoardReplyEntities().add(this);
    }

    public static BoardReplyEntity createBoardReplyEntity(
            final Long id,
            final String reply,
            final BoardEntity boardEntity,
            final StaffEntity staffEntity) {

        final BoardReplyEntity boardReplyEntity = new BoardReplyEntity();
        boardReplyEntity.id = id;
        boardReplyEntity.reply = reply;
        boardReplyEntity.setBoardEntity(boardEntity);
        boardReplyEntity.setStaffEntity(staffEntity);

        return boardReplyEntity;
    }
}
