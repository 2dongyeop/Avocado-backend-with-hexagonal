package io.wisoft.avocadobackendhexagonal.domain.board.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.ReadBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.BoardCommand;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.LoadBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReadBoardService implements ReadBoardUseCase {

    private final LoadBoardPort loadBoardPort;

    @Override
    public BoardCommand readBoard(final Long boardId) {
        final Board board = loadBoardPort.findById(boardId);

        return boardToBoardCommand(board);
    }

    @Override
    public Page<BoardCommand> readBoardList(final Pageable pageable) {
        return new PageImpl<>(loadBoardPort.findAll(pageable).stream()
                .map(board -> boardToBoardCommand(board))
                .collect(Collectors.toList()));
    }

    private static BoardCommand boardToBoardCommand(final Board board) {
        return new BoardCommand(board.getTitle(), board.getBody(), board.getMember().getEmail());
    }
}
