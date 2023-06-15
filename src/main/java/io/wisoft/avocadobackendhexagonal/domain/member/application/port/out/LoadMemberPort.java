package io.wisoft.avocadobackendhexagonal.domain.member.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;

import java.util.List;

public interface LoadMemberPort {

    Member findById(final Long memberId);

    List<Member> findAll();

    Member findByEmail(final String email);
}
