package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalEntity;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import org.springframework.stereotype.Component;

@Component
public class HospitalMapper {

    public static HospitalEntity hospitalToHospitalEntity(final Hospital hospital) {
        return HospitalEntity.createHospital(
                hospital.getId() == null ? null : hospital.getId(),
                hospital.getName(),
                hospital.getNumber(),
                hospital.getAddress(),
                hospital.getOperatingTime()
        );
    }

    public static Hospital hospitalEntityToHospital(final HospitalEntity hospitalEntity) {
        return Hospital.registerHospital(
                hospitalEntity.getId(),
                hospitalEntity.getName(),
                hospitalEntity.getNumber(),
                hospitalEntity.getAddress(),
                hospitalEntity.getOperatingTime()
        );
    }
}
