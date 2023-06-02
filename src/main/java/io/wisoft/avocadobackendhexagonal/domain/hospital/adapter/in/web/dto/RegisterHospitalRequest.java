package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public record RegisterHospitalRequest(
        @NotBlank String name,
        @NotBlank String number,
        @NotBlank String address,
        @NotBlank String operatingTime) {
}
