package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto;

import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;

public record HospitalDto(
        Long id,
        String name,
        String number,
        String address,
        String operatingTime
) {
    public static HospitalDto of(final Hospital hospital) {
        return new HospitalDto(
                hospital.getId(),
                hospital.getName(),
                hospital.getNumber(),
                hospital.getAddress(),
                hospital.getOperatingTime()
        );
    }
}
