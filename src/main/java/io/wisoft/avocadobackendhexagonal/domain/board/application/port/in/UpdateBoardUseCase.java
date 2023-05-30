package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.UpdateBoardCommand;

public interface UpdateBoardUseCase {

    void update(final UpdateBoardCommand command);
}
