package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupMemberRequest;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupMemberCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final SignupUseCase signupUseCase;

    @PostMapping("/api/auth/signup/members")
    public ResponseEntity<Long> signupMember(@RequestBody @Valid final SignupMemberRequest request) {

        final SignupMemberCommand signupMemberCommand = getCommand(request);
        return ResponseEntity.ok(signupUseCase.signup(signupMemberCommand));
    }




    private SignupMemberCommand getCommand(final SignupMemberRequest request) {
        return new SignupMemberCommand(
                request.email(),
                request.nickname(),
                request.password(),
                request.phoneNumber(),
                request.memberPhotoPath()
        );
    }
}
