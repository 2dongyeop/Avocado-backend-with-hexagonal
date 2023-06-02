package io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;

public interface SaveHospitalPort {

    Long save(final Hospital hospital);
}
