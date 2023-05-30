package io.wisoft.avocadobackendhexagonal.domain.board.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;

public interface SaveBoardPort {
    Long save(final Board board);
}
