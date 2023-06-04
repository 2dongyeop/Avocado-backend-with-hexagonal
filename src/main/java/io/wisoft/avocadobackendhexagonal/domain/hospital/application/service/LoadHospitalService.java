package io.wisoft.avocadobackendhexagonal.domain.hospital.application.service;

import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.LoadHospitalUseCase;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.LoadHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadHospitalService implements LoadHospitalUseCase {

    private final LoadHospitalPort loadHospitalPort;

    @Override
    public Hospital loadHospital(final Long hospitalId) {
        return loadHospitalPort.findById(hospitalId);
    }

    @Override
    public List<Hospital> loadHospitalList() {
        return loadHospitalPort.findAll();
    }
}

