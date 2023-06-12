package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupMemberRequest;
import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupStaffRequest;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupMemberCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;
import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.Illegal.CustomIllegalException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class SignupController {

    private final SignupUseCase signupUseCase;

    @PostMapping("/signup/members")
    public ResponseEntity<Long> signupMember(@RequestBody @Valid final SignupMemberRequest request) {

        validatePassword(request.password1(), request.password2());

        final SignupMemberCommand signupMemberCommand = getSignupMemberCommand(request);
        return ResponseEntity.ok(signupUseCase.signupMember(signupMemberCommand));
    }


    @PostMapping("/signup/staff")
    public ResponseEntity<Long> signupStaff(@RequestBody @Valid final SignupStaffRequest request) {

        final SignupStaffCommand signupStaffCommand = getSignupStaffCommand(request);
        return ResponseEntity.ok(signupUseCase.signupStaff(signupStaffCommand));
    }

    private void validatePassword(final String password, final String confirmPassword) {

        if (!password.equals(confirmPassword)) {
            throw new CustomIllegalException("비밀번호와 확인비밀번호가 다릅니다.", ErrorCode.ILLEGAL_INPUT);
        }
    }

    private SignupStaffCommand getSignupStaffCommand(final SignupStaffRequest request) {
        return new SignupStaffCommand(
                request.name(),
                request.email(),
                request.password1(),
                request.license_path(),
                request.dept(),
                request.hospitalId()
        );
    }


    private SignupMemberCommand getSignupMemberCommand(final SignupMemberRequest request) {
        return new SignupMemberCommand(
                request.email(),
                request.nickname(),
                request.password1(),
                request.phoneNumber(),
                request.memberPhotoPath()
        );
    }
}
