package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;

public interface DeleteMemberUseCase {

    void delete(final Long memberId);
}
