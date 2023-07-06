package io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.in.comand.WriteBoardReplyCommand;

public interface WriteBoardReplyUseCase {

    void writeBoardReply(final WriteBoardReplyCommand writeBoardReplyCommand);
}
