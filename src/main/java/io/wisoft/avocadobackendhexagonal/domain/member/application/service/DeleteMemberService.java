package io.wisoft.avocadobackendhexagonal.domain.member.application.service;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.DeleteMemberUseCase;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.DeleteMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteMemberService implements DeleteMemberUseCase {

    private final DeleteMemberPort deleteMemberPort;
    private final LoadMemberPort loadMemberPort;

    @Override
    @Transactional
    public void delete(final Long memberId) {
        final Member member = loadMemberPort.findById(memberId);
        deleteMemberPort.delete(member);
    }
}
