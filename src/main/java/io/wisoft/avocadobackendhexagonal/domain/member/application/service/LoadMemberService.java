package io.wisoft.avocadobackendhexagonal.domain.member.application.service;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.LoadMemberUseCase;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoadMemberService implements LoadMemberUseCase {

    private final LoadMemberPort loadMemberPort;

    @Override
    public Member loadMember(final Long memberId) {
        return loadMemberPort.findById(memberId);
    }

    @Override
    public List<Member> loadMemberList() {
        return loadMemberPort.findAll();
    }
}
