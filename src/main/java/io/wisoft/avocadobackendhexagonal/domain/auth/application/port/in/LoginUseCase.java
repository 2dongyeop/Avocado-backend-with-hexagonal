package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.LoginCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.TokenCommand;

public interface LoginUseCase {

    TokenCommand loginStaff(final LoginCommand command);
    TokenCommand loginMember(final LoginCommand command);
}
