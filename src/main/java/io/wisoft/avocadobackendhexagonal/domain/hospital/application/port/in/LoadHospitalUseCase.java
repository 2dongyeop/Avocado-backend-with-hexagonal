package io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;

import java.util.List;

public interface LoadHospitalUseCase {
    Hospital loadHospital(final Long hospitalId);
    List<Hospital> loadHospitalList();
}
