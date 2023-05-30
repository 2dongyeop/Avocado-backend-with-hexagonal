package io.wisoft.avocadobackendhexagonal.domain.board.domain;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    private Long id;
    private String title;
    private String body;
    private Member member;

    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public Board(final Long id, final String title, final String body, final Member member) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.member = member;

        this.createdTime = LocalDateTime.now();
        this.modifiedTime = LocalDateTime.now();
    }

    public static Board withoutId(final String title, final String body, final Member member) {
        Board board = new Board();
        board.id = null;
        board.title = title;
        board.body = body;
        board.member = member;

        board.createdTime = LocalDateTime.now();
        board.modifiedTime = LocalDateTime.now();

        return board;
    }

    public void update(final String newBody) {
        this.body = newBody;
        this.modifiedTime = LocalDateTime.now();
    }
}
