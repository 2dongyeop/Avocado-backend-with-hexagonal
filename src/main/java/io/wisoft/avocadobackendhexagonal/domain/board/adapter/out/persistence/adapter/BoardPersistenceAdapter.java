package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardEntity;
import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardMapper;
import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardRepository;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.DeleteBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.LoadBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.SaveBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.NotFoundBoardException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements SaveBoardPort, LoadBoardPort, DeleteBoardPort {

    private final BoardRepository boardRepository;

    @Override
    public Long save(final Board board) {
        return boardRepository.save(BoardMapper.boardToBoardEntity(board)).getId();
    }

    @Override
    public Board findById(final Long boardId) {
        final BoardEntity boardEntity = boardRepository.findById(boardId).orElseThrow(NotFoundBoardException::new);
        return BoardMapper.boardEntityToBoard(boardEntity);
    }

    @Override
    public Page<Board> findAll(final Pageable pageable) {
        return new PageImpl<>(boardRepository.findAll(pageable).stream()
                .map(BoardMapper::boardEntityToBoard)
                .collect(Collectors.toList()));
    }

    @Override
    public void delete(final Board board) {
        boardRepository.delete(BoardMapper.boardToBoardEntity(board));
    }
}
