package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command;

public record SignupStaffCommand(
        String name,
        String email,
        String password,
        String license_path,
        String dept,
        Long hospitalId
) {
}
