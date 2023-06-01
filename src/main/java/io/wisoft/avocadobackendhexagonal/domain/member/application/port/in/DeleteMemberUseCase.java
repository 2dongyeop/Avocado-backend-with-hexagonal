package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in;

public interface DeleteMemberUseCase {

    void delete(final Long memberId);
}
