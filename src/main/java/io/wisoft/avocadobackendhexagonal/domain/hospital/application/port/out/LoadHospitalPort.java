package io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;

import java.util.List;

public interface LoadHospitalPort {

    Hospital findById(final Long hospitalId);

    List<Hospital> findAll();

    boolean findByExistsByName(final String name);
}
