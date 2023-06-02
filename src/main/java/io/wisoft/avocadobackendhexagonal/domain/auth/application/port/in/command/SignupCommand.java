package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

import jakarta.validation.constraints.NotBlank;

public record SignupCommand(
        @NotBlank String email,
        @NotBlank String nickname,
        @NotBlank String password,
        @NotBlank String phoneNumber,
        @NotBlank String memberPhotoPath
) {
}
