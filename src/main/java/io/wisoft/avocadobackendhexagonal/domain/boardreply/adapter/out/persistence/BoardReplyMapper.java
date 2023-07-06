package io.wisoft.avocadobackendhexagonal.domain.boardreply.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardMapper;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.domain.BoardReply;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffMapper;
import org.springframework.stereotype.Component;

@Component
public class BoardReplyMapper {

    public static BoardReplyEntity boardReplyToBoardReplyEntity(final BoardReply boardReply) {

        return BoardReplyEntity.createBoardReplyEntity(
                boardReply.getId() == null ? null : boardReply.getId(),
                boardReply.getReply(),
                BoardMapper.boardToBoardEntity(boardReply.getBoard()),
                StaffMapper.staffToStaffEntity(boardReply.getStaff())
        );
    }

    public static BoardReply boardReplyEntityToBoardReply(final BoardReplyEntity boardReplyEntity) {

        return BoardReply.createBoardReply(
                boardReplyEntity.getId(),
                boardReplyEntity.getReply(),
                BoardMapper.boardEntityToBoard(boardReplyEntity.getBoardEntity()),
                StaffMapper.staffEntityToStaff(boardReplyEntity.getStaffEntity())
        );
    }
}
