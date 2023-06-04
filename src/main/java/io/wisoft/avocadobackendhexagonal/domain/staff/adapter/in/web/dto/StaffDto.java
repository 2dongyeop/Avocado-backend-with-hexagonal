package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StaffDto(
        @NotNull Long id,
        @NotBlank String name,
        @NotBlank String email,
        @NotBlank String license_path,
        @NotBlank String dept,
        @NotBlank String hospital
) {
}
