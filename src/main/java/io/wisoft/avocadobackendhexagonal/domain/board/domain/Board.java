package io.wisoft.avocadobackendhexagonal.domain.board.domain;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    private Long id;
    private String title;
    private String body;
    private Member member;

    public Board(final Long id, final String title, final String body, final Member member) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.member = member;
    }

    public static Board simpleBoard(final String title, final String body, final Member member) {
        Board board = new Board();
        board.id = null;
        board.title = title;
        board.body = body;
        board.member = member;

        return board;
    }
}
