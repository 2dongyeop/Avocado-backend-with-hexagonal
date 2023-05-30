package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardMapper;
import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardRepository;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.SaveBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements SaveBoardPort {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Override
    public Long save(final Board board) {
        return boardRepository.save(boardMapper.boardToBoardEntity(board)).getId();
    }
}
