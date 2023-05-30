package io.wisoft.avocadobackendhexagonal.domain.member.application.service;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.command.SignupCommand;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SignUpPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignupUseCase {

    private final SignUpPort signUpPort;

    @Override
    public Long signup(final SignupCommand request) {

        final Member member = Member.withoutId(request.email(), request.password());

        return signUpPort.signup(member);
    }
}
