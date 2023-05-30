package io.wisoft.avocadobackendhexagonal.domain.member.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence.MemberEntity;

public interface LoadMemberPort {

    MemberEntity findById(final Long memberId);
}
