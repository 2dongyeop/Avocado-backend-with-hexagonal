package io.wisoft.avocadobackendhexagonal.domain.board.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadBoardPort {

    Board findById(final Long boardId);

    Page<Board> findAll(final Pageable pageable);
}
