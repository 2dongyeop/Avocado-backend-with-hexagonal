package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupStaffCommand(
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String license_path,
        @NotBlank String dept,
        @NotNull Long hospitalId
) {
}
