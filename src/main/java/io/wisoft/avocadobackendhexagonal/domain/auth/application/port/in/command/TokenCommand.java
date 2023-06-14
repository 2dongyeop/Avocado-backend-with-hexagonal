package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

public record TokenCommand(
        String tokenType,
        String accessToken,
        String refreshToken
) {
}
