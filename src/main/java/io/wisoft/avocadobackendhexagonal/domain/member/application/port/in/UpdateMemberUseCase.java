package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in;

public interface UpdateMemberUseCase {

    Long updateMember(final Long memberId, final String email);
}
