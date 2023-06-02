package io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.command;

import jakarta.validation.constraints.NotBlank;

public record RegisterHospitalCommand (
        @NotBlank String name,
        @NotBlank String number,
        @NotBlank String address,
        @NotBlank String operatingTime
){ }
