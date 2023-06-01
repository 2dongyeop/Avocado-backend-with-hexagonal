package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberMapper;
import org.springframework.stereotype.Component;


@Component
public class BoardMapper {

    public static BoardEntity boardToBoardEntity(final Board board) {

        return BoardEntity.createBoardEntity(
                board.getId() == null ? null : board.getId().longValue(),
                board.getTitle(),
                board.getBody(),
                board.getBoardPhotoPath(),
                board.getDept(),
                MemberMapper.memberToMemberEntity(board.getMember())
        );
    }

    public static Board boardEntityToBoard(final BoardEntity boardEntity) {

        return Board.createBoard(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getBody(),
                boardEntity.getBoardPhotoPath(),
                boardEntity.getStatus(),
                boardEntity.getDept(),
                MemberMapper.memberEntityToMember(boardEntity.getMemberEntity())
        );
    }
}
