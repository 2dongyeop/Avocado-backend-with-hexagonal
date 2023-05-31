package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;

import java.util.List;

public interface LoadMemberUseCase {

    Member loadMember(final Long memberId);
    List<Member> loadMemberList();
}
