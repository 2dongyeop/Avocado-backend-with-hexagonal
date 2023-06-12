package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

public record LoginCommand(
        String email,
        String password
) {
}
