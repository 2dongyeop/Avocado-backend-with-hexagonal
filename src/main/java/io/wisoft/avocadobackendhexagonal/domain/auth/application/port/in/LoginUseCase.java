package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.LoginCommand;

public interface LoginUseCase {

    String loginStaff(final LoginCommand command);
    String loginMember(final LoginCommand command);
}
