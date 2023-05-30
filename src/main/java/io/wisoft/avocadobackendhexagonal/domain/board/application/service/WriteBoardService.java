package io.wisoft.avocadobackendhexagonal.domain.board.application.service;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.WriteBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.WriteBoardCommand;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.out.SaveBoardPort;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
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
        final Board board = Board.withoutId(request.title(), request.body(), member);

        return saveBoardPort.save(board);
    }
}
