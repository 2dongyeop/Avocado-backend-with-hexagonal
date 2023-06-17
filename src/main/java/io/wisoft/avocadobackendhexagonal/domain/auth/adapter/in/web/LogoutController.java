package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.LogoutUseCase;
import io.wisoft.avocadobackendhexagonal.global.jwt.AuthorizationExtractor;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final AuthorizationExtractor authExtractor;
    private final LogoutUseCase logoutUseCase;


    @PostMapping("/logout")
    public ResponseEntity<String> logoutMember(final HttpServletRequest request) {

        final String accessToken = authExtractor.extract(request, "Bearer");

        logoutUseCase.logout(accessToken);
        return ResponseEntity.ok("logout success");
    }
}
