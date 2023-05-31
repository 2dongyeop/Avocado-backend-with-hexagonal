package io.wisoft.avocadobackendhexagonal.domain.auth.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupCommand;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SaveMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignupUseCase {

    private final SaveMemberPort saveMemberPort;

    @Override
    public Long signup(final SignupCommand request) {

        final Member member = Member.withoutId(request.email(), request.password());

        return saveMemberPort.save(member);
    }
}
