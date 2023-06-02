package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupRequest;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupCommand;
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

    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody @Valid SignupRequest request) {

        final SignupCommand signupCommand = getCommand(request);
        return ResponseEntity.ok(signupUseCase.signup(signupCommand));
    }

    private static SignupCommand getCommand(final SignupRequest request) {
        return new SignupCommand(
                request.email(),
                request.nickname(),
                request.password(),
                request.phoneNumber(),
                request.memberPhotoPath()
        );
    }
}
