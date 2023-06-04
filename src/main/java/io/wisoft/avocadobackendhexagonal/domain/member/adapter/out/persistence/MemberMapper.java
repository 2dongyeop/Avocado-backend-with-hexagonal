package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardEntity;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapper {

    public static MemberEntity memberToMemberEntity(final Member member) {
        final MemberEntity memberEntity = MemberEntity.createMemberEntity(
                member.getId() == null? null : member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPassword(),
                member.getPhoneNumber(),
                member.getMemberPhotoPath()
        );

        final List<BoardEntity> boardEntities = extractBoardEntities(member, memberEntity);
        memberEntity.setBoards(boardEntities);

        return memberEntity;
    }

    private static List<BoardEntity> extractBoardEntities(final Member member, final MemberEntity memberEntity) {
        final List<BoardEntity> boardEntities = new ArrayList<>();
        for (Board board : member.getBoards()) {
            BoardEntity boardEntity = BoardEntity.createBoardEntity(
                    board.getId(),
                    board.getTitle(),
                    board.getBody(),
                    board.getBoardPhotoPath(),
                    board.getDept(),
                    memberEntity
            );
            boardEntities.add(boardEntity);
        }
        return boardEntities;
    }

    public static Member memberEntityToMember(final MemberEntity memberEntity) {


        final Member member = Member.createMember(
                memberEntity.getId(),
                memberEntity.getEmail(),
                memberEntity.getNickname(),
                memberEntity.getPassword(),
                memberEntity.getPhoneNumber(),
                memberEntity.getMemberPhotoPath()
        );

        final List<Board> boards = extractBoards(memberEntity, member);
        member.setBoards(boards);

        return member;
    }

    private static List<Board> extractBoards(MemberEntity memberEntity, Member member) {
        final List<Board> boards = new ArrayList<>();
        for (BoardEntity boardEntity : memberEntity.getBoards()) {
            final Board board = Board.createBoard(
                    boardEntity.getId(),
                    boardEntity.getTitle(),
                    boardEntity.getBody(),
                    boardEntity.getBoardPhotoPath(),
                    boardEntity.getStatus(),
                    boardEntity.getDept(),
                    member
            );
            boards.add(board);
        }
        return boards;
    }
}
