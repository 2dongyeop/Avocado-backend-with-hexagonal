package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.BoardCommand;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

    String title;
    String body;
    String boardPhotoPath;
    String dept;
    String writer;

    public BoardDto(Board board) {
        this.title = board.getTitle();
        this.body = board.getBody();
        this.boardPhotoPath = board.getBoardPhotoPath();
        this.dept = board.getDept().name();
        this.writer = board.getMember().getNickname();
    }

    public BoardDto(BoardCommand boardCommand) {
        this.title = boardCommand.title();
        this.body = boardCommand.body();
        this.boardPhotoPath = boardCommand.boardPhotoPath();
        this.dept = boardCommand.dept();
        this.writer = boardCommand.writer();
    }
}