package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SignupStaffRequest(
        @NotBlank
        String name,
        @NotBlank @Email
        String email,
        @NotBlank String password1,
        @NotBlank String password2,
        @NotBlank String license_path,
        @NotBlank String dept,
        @NotNull Long hospitalId
) {
}
