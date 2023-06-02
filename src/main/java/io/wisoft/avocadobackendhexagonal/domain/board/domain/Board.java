package io.wisoft.avocadobackendhexagonal.domain.board.domain;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import io.wisoft.avocadobackendhexagonal.global.basetime.BaseTimeDomain;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import io.wisoft.avocadobackendhexagonal.global.enumeration.status.BoardStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeDomain {

    private Long id;
    private String title;
    private String body;
    private String boardPhotoPath;
    private BoardStatus status;
    private HospitalDept dept;
    private Member member;

    public void setMember(final Member member) {
        //comment: 기존 관계 제거
        if (this.member != null) {
            this.member.getBoards().remove(this);
        }

        this.member = member;

        //comment: 무한루프 방지
        if (!member.getBoards().contains(this)) {
            member.getBoards().add(this);
        }
    }

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
        board.createDomain();

        return board;
    }

    public void update(final String newBody) {
        this.body = newBody;
        this.updateDomain();
    }
}
