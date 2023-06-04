package io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record SignupMemberRequest(
        @NotBlank String email,
        @NotBlank String nickname,
        @NotBlank String password,
        @NotBlank String phoneNumber,
        @NotBlank String memberPhotoPath
) {
}
