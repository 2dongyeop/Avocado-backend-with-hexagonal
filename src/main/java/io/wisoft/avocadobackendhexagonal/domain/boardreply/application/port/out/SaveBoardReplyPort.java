package io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.boardreply.domain.BoardReply;

public interface SaveBoardReplyPort {

    Long save(final BoardReply boardReply);
}
