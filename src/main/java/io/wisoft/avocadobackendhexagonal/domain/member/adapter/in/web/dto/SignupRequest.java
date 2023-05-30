package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank String email,
        @NotBlank String password
) {
}
