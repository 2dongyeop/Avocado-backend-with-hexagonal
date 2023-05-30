package io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.command;

import jakarta.validation.constraints.NotBlank;

public record SignupCommand(
        @NotBlank String email,
        @NotBlank String password
) {
}
