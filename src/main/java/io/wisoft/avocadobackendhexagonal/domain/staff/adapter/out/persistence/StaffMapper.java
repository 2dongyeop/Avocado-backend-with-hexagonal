package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalMapper;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import org.springframework.stereotype.Component;

@Component
public class StaffMapper {

    public static StaffEntity staffToStaffEntity(final Staff staff) {

        return StaffEntity.createStaff(
                staff.getId() == null ? null : staff.getId(),
                staff.getName(),
                staff.getEmail(),
                staff.getPassword(),
                staff.getLicense_path(),
                staff.getDept(),
                HospitalMapper.hospitalToHospitalEntity(staff.getHospital())
        );
    }


    public static Staff staffEntityToStaff(final StaffEntity staffEntity) {

        return Staff.createStaff(
                staffEntity.getId(),
                staffEntity.getName(),
                staffEntity.getEmail(),
                staffEntity.getPassword(),
                staffEntity.getLicense_path(),
                staffEntity.getDept(),
                HospitalMapper.hospitalEntityToHospital(staffEntity.getHospital())
        );
    }
}
