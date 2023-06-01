package io.wisoft.avocadobackendhexagonal.domain.board.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.DeleteBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.DeleteBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.LoadBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBoardService implements DeleteBoardUseCase {

    private final DeleteBoardPort deleteBoardPort;
    private final LoadBoardPort loadBoardPort;

    @Override
    public void delete(final Long boardId) {
        final Board board = loadBoardPort.findById(boardId);
        deleteBoardPort.delete(board);
    }
}
