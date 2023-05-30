package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.WriteBoardCommand;

public interface WriteBoardUseCase {

    Long writeBoard(final WriteBoardCommand request);
}
