package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import org.springframework.stereotype.Component;

import static io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberMapper.memberEntityToMember;
import static io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberMapper.memberToMemberEntity;

@Component
public class BoardMapper {

    public BoardEntity boardToBoardEntity(final Board board) {

        return new BoardEntity(
                board.getId() == null ? null : board.getId().longValue(),
                board.getTitle(),
                board.getBody(),
                memberToMemberEntity(board.getMember()) == null ? null : memberToMemberEntity(board.getMember())
        );
    }

    public Board boardEntityToBoard(final BoardEntity boardEntity) {

        return new Board(
                boardEntity.getId(),
                boardEntity.getTitle(),
                boardEntity.getBody(),
                memberEntityToMember(boardEntity.getMember())
        );
    }
}
