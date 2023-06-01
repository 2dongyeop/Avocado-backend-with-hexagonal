package io.wisoft.avocadobackendhexagonal.domain.board.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.WriteBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.WriteBoardCommand;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.SaveBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import io.wisoft.avocadobackendhexagonal.global.enumeration.status.BoardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteBoardService implements WriteBoardUseCase {

    private final SaveBoardPort saveBoardPort;
    private final LoadMemberPort loadMemberPort;

    @Override
    public Long writeBoard(final WriteBoardCommand request) {
        final Member member = loadMemberPort.findById(request.memberId());
        final Board board = Board.createBoard(
                null,
                request.title(),
                request.body(),
                request.boardPhotoPath(),
                BoardStatus.WRITE,
                HospitalDept.valueOf(request.dept()),
                member
        );

        return saveBoardPort.save(board);
    }
}
