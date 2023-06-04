package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateStaffPasswordRequest(
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
