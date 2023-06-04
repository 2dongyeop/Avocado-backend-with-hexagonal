package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web;

public record HospitalDto(
        Long id,
        String name,
        String number,
        String address,
        String operatingTime
) {
}
