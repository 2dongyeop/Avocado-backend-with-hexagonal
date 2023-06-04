package io.wisoft.avocadobackendhexagonal.domain.member.application.service;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SaveMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.UpdateMemberUseCase;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMemberService implements UpdateMemberUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;

    @Override
    @Transactional
    public Long updateMember(final Long memberId, final String email) {

        final Member member = loadMemberPort.findById(memberId);
        member.update(email);

        return saveMemberPort.save(member);
    }
}
