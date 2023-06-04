package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupMemberCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;

public interface SignupUseCase {

    Long signupMember(final SignupMemberCommand request);
    Long signupStaff(final SignupStaffCommand request);

}
