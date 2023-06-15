package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.TokenCommand;

public record TokenResponse(
        String tokenType,
        String accessToken,
        String refreshToken
) {
    public static TokenResponse of(final TokenCommand tokenCommand) {
        return new TokenResponse(
                tokenCommand.tokenType(),
                tokenCommand.accessToken(),
                tokenCommand.refreshToken()
        );
    }
}
