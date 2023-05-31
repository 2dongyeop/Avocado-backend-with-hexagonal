package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupCommand;

public interface SignupUseCase {

    Long signup(final SignupCommand request);
}
