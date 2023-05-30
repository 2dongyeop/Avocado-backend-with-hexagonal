package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.BoardCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReadBoardUseCase {

    BoardCommand readBoard(final Long boardId);
    Page<BoardCommand> readBoardList(final Pageable pageable);
}
