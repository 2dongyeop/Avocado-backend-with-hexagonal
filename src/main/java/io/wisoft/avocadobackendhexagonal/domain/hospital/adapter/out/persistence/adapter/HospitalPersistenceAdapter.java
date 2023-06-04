package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalEntity;
import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalMapper;
import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalRepository;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.LoadHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.NotFoundHospitalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HospitalPersistenceAdapter implements SaveHospitalPort, LoadHospitalPort {

    private final HospitalRepository hospitalRepository;


    @Override
    public Long save(final Hospital hospital) {
        return hospitalRepository.save(HospitalMapper.hospitalToHospitalEntity(hospital)).getId();
    }

    @Override
    public Hospital findById(final Long hospitalId) {
        final HospitalEntity hospitalEntity = hospitalRepository.findById(hospitalId)
                .orElseThrow(NotFoundHospitalException::new);

        return HospitalMapper.hospitalEntityToHospital(hospitalEntity);
    }

    @Override
    public List<Hospital> findAll() {
        return hospitalRepository.findAll().stream()
                .map(hospitalEntity
                        -> HospitalMapper.hospitalEntityToHospital(hospitalEntity))
                .toList();
    }

    @Override
    public boolean findByExistsByName(final String name) {
        return hospitalRepository.existsByName(name);
    }
}
