package io.wisoft.avocadobackendhexagonal.domain.boardreply.domain;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardReply {

    private Long id;

    private String reply;

    private Board board;

    private Staff staff;

    public void setBoard(final Board board) {
        this.board = board;

        board.getBoardReplies().add(this);
    }

    public void setStaff(final Staff staff) {
        this.staff = staff;

        staff.getBoardReplies().add(this);
    }

    public static BoardReply createBoardReply(
            final Long id,
            final String reply,
            final Board board,
            final Staff staff) {

        final BoardReply boardReply = new BoardReply();
        boardReply.id = id;
        boardReply.reply = reply;
        boardReply.setBoard(board);
        boardReply.setStaff(staff);

        return boardReply;
    }
}
