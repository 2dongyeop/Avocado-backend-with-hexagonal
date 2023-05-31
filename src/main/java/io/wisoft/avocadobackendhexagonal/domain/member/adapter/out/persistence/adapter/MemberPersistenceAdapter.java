package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberEntity;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberMapper;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberRepository;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.DeleteMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SaveMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.LoadMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, SaveMemberPort, DeleteMemberPort {

    private final MemberRepository memberRepository;

    @Override
    public Member findById(final Long memberId) {
        final MemberEntity memberEntity = memberRepository.findById(memberId)
                .orElseThrow(IllegalArgumentException::new);

        return MemberMapper.memberEntityToMember(memberEntity);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll().stream()
                .map(memberEntity ->  MemberMapper.memberEntityToMember(memberEntity))
                .toList();
    }

    @Override
    public Long save(final Member member) {
        return memberRepository.save(MemberMapper.memberToMemberEntity(member)).getId();
    }

    @Override
    public void delete(final Member member) {
        memberRepository.delete(MemberMapper.memberToMemberEntity(member));
    }
}
