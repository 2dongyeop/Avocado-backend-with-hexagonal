package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto.BoardDto;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.ReadBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.BoardCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReadBoardController {

    private final ReadBoardUseCase readBoardUseCase;

    @GetMapping("/api/boards/{id}/details")
    public BoardDto board(@PathVariable final Long id) {
        return boardCommandToBoardDto(readBoardUseCase.readBoard(id));
    }

    @GetMapping("/api/boards")
    public Page<BoardDto> readBoardList(final Pageable pageable) {
        return new PageImpl<>(readBoardUseCase.readBoardList(pageable).stream()
                .map(boardCommand -> boardCommandToBoardDto(boardCommand))
                .toList());
    }

    private BoardDto boardCommandToBoardDto(final BoardCommand boardCommand) {
        return new BoardDto(boardCommand);
    }
}
