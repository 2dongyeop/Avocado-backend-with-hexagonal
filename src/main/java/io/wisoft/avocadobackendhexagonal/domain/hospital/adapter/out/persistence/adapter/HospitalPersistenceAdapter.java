package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalRepository;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HospitalPersistenceAdapter implements SaveHospitalPort {

    private final HospitalRepository hospitalRepository;


    @Override
    public Long save(final Hospital hospital) {
        return hospitalRepository.save(HospitalMapper.hospitalToHospitalEntity(hospital)).getId();
    }
}
