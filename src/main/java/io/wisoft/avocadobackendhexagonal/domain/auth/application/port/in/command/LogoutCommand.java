package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

public record LogoutCommand(
        String token
) {
}
