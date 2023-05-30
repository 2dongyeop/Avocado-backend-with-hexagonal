package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in;

public interface DeleteBoardUseCase {
    void delete(final Long boardId);
}
