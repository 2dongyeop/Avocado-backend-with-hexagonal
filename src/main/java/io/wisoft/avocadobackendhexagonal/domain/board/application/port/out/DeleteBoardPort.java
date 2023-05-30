package io.wisoft.avocadobackendhexagonal.domain.board.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;

public interface DeleteBoardPort {

    void delete(final Board boardId);
}
