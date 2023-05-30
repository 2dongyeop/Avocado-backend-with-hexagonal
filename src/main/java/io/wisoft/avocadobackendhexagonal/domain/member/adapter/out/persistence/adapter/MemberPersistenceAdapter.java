package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberEntity;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberMapper;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberRepository;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SignUpPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements SignUpPort, LoadMemberPort {

    private final MemberRepository memberRepository;


    @Override
    public Long signup(final Member member) {
        return memberRepository.save(MemberMapper.memberToMemberEntity(member)).getId();
    }

    @Override
    public MemberEntity findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(IllegalArgumentException::new);
    }
}
