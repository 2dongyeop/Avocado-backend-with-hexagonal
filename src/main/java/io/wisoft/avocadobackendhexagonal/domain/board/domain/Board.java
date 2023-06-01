package io.wisoft.avocadobackendhexagonal.domain.board.domain;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import io.wisoft.avocadobackendhexagonal.global.enumeration.status.BoardStatus;
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
    private String boardPhotoPath;
    private BoardStatus status;
    private HospitalDept dept;
    private Member member;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public static Board createBoard(
            final Long id,
            final String title,
            final String body,
            final String boardPhotoPath,
            final BoardStatus status,
            final HospitalDept dept,
            final Member member) {

        final Board board = new Board();
        board.id = id;
        board.title = title;
        board.body = body;
        board.boardPhotoPath = boardPhotoPath;
        board.status = status;
        board.dept = dept;
        board.member = member;
        board.createdDate = LocalDateTime.now();
        board.lastModifiedDate = LocalDateTime.now();

        return board;
    }

    public void update(final String newBody) {
        this.body = newBody;
        this.lastModifiedDate = LocalDateTime.now();
    }
}
