package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.LoginRequest;
import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.TokenResponse;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.LoginUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.LoginCommand;
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
public class LoginController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login/members")
    public ResponseEntity<TokenResponse> signupMember(@RequestBody @Valid final LoginRequest request) {

        final LoginCommand loginCommand = getLoginCommand(request);
        final String token = loginUseCase.loginMember(loginCommand);
        return ResponseEntity.ok(new TokenResponse(token, "bearer"));
    }


    @PostMapping("/login/staff")
    public ResponseEntity<TokenResponse> loginStaff(@RequestBody @Valid final LoginRequest request) {

        final LoginCommand loginCommand = getLoginCommand(request);
        final String token = loginUseCase.loginStaff(loginCommand);
        return ResponseEntity.ok(new TokenResponse(token, "bearer"));
    }

    private LoginCommand getLoginCommand(final LoginRequest request) {
        final LoginCommand loginCommand = new LoginCommand(request.email(), request.password());
        return loginCommand;
    }
}
