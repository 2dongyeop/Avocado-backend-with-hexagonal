package io.wisoft.avocadobackendhexagonal.domain.board.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.UpdateBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.UpdateBoardCommand;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.LoadBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.SaveBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBoardService implements UpdateBoardUseCase {

    private final LoadBoardPort loadBoardPort;
    private final SaveBoardPort saveBoardPort;

    @Transactional
    @Override
    public void update(final UpdateBoardCommand command) {

        final Board board = loadBoardPort.findById(command.boardId());
        board.update(command.body());

        saveBoardPort.save(board);
    }
}
