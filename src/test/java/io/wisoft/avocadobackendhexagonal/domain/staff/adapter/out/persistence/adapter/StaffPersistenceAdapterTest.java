package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.adapter.HospitalPersistenceAdapter;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import io.wisoft.avocadobackendhexagonal.settings.common.PersistenceAdapterTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@Import({StaffPersistenceAdapter.class, HospitalPersistenceAdapter.class})
class StaffPersistenceAdapterTest extends PersistenceAdapterTest {

    @Autowired
    private HospitalPersistenceAdapter hospitalPersistenceAdapter;

    @Autowired
    private StaffPersistenceAdapter staffPersistenceAdapter;

    @Test
    public void save_success() throws Exception {
        //given -- 조건

        final Hospital hospital = getHospital();

        hospitalPersistenceAdapter.save(hospital);

        final Staff staff = getStaff(hospital);

        //when -- 동작
        final Long saveId = staffPersistenceAdapter.save(staff);

        //then -- 검증
        final Staff findStaff = staffPersistenceAdapter.findById(saveId);

        Assertions.assertThat(findStaff.getName()).isEqualTo("name");
    }


    @Test
    public void delete_success() throws Exception {
        final Hospital hospital = getHospital();

        hospitalPersistenceAdapter.save(hospital);

        final Staff staff = getStaff(hospital);
        final Long saveId = staffPersistenceAdapter.save(staff);

        //when -- 동작
        final StaffEntity findStaffEntity = staffPersistenceAdapter.findStaffEntityById(saveId);
        staffPersistenceAdapter.delete(findStaffEntity);

        //then -- 검증
        Assertions.assertThat(staffPersistenceAdapter.existsByEmail(staff.getEmail())).isFalse();
    }


    private Staff getStaff(final Hospital hospital) {
        return Staff.createStaff(
                1L,
                "name",
                "email",
                "password",
                "license",
                "photo",
                HospitalDept.DENTAL,
                hospital
        );
    }

    private Hospital getHospital() {
        return Hospital.registerHospital(
                null,
                "name",
                "number",
                "address",
                "operatingTime"
        );
    }

}