package io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.command;

import jakarta.validation.constraints.NotBlank;

public record UpdateStaffPasswordCommand(
        @NotBlank String oldPassword,
        @NotBlank String newPassword
) {
}
