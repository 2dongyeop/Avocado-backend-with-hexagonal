package io.wisoft.avocadobackendhexagonal.domain.boardreply.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.LoadBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.in.WriteBoardReplyUseCase;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.in.comand.WriteBoardReplyCommand;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.out.SaveBoardReplyPort;
import io.wisoft.avocadobackendhexagonal.domain.boardreply.domain.BoardReply;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WriteBoardReplyService implements WriteBoardReplyUseCase {

    private final SaveBoardReplyPort saveBoardReplyPort;
    private final LoadBoardPort loadBoardPort;
    private final LoadStaffPort loadStaffPort;

    @Override
    @Transactional
    public void writeBoardReply(final WriteBoardReplyCommand command) {

        final Board board = loadBoardPort.findById(command.boardId());
        final Staff staff = loadStaffPort.findById(command.staffId());

        final BoardReply boardReply = BoardReply.createBoardReply(
                null,
                command.reply(),
                board,
                staff
        );

        saveBoardReplyPort.save(boardReply);
    }
}
