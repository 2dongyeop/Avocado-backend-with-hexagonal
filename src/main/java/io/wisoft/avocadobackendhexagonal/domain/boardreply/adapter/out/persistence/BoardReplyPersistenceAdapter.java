package io.wisoft.avocadobackendhexagonal.domain.boardreply.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.out.SaveBoardReplyPort;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.domain.BoardReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardReplyPersistenceAdapter implements SaveBoardReplyPort {

    private final BoardReplyRepository boardReplyRepository;

    @Override
    public Long save(final BoardReply boardReply) {

        return boardReplyRepository.save(BoardReplyMapper.boardReplyToBoardReplyEntity(boardReply)).getId();
    }
}
