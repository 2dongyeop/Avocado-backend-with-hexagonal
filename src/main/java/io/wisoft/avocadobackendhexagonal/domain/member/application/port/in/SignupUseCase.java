package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.command.SignupCommand;

public interface SignupUseCase {

    Long signup(final SignupCommand request);
}
