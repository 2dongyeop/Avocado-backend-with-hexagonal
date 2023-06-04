package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupMemberCommand;

public interface SignupUseCase {

    Long signup(final SignupMemberCommand request);
}
