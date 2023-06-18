package io.wisoft.avocadobackendhexagonal.domain.staff.domain;

import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StaffTest {

    @Test
    public void updateHospital() throws Exception {
        //given -- 조건
        final Staff staff = getStaff();

        //when -- 동작
        staff.updateHospital(
                Hospital.registerHospital(
                        2L,
                        "updateName",
                        "updateNumber",
                        "updateAddress",
                        "operatingTime"
                )
        );

        //then -- 검증
        Assertions.assertThat(staff.getHospital().getId()).isEqualTo(2L);
        Assertions.assertThat(staff.getHospital().getName()).isEqualTo("updateName");
        Assertions.assertThat(staff.getHospital().getNumber()).isEqualTo("updateNumber");
        Assertions.assertThat(staff.getHospital().getAddress()).isEqualTo("updateAddress");
        Assertions.assertThat(staff.getHospital().getOperatingTime()).isEqualTo("operatingTime");
    }


    @Test
    public void updatePhotoPath() throws Exception {
        //given -- 조건
        final Staff staff = getStaff();

        //when -- 동작
        staff.updatePhotoPath("newPhotoPath");

        //then -- 검증
        Assertions.assertThat(staff.getStaffPhotoPath()).isEqualTo("newPhotoPath");
    }


    @Test
    public void updatePassword() throws Exception {
        //given -- 조건
        final Staff staff = getStaff();

        //when -- 동작
        staff.updatePassword("newPassword");

        //then -- 검증
        Assertions.assertThat(staff.getPassword()).isEqualTo("newPassword");
    }

    private Staff getStaff() {
        return Staff.createStaff(
                1L,
                "name",
                "email",
                "password",
                "license",
                "photo",
                HospitalDept.DENTAL,
                Hospital.registerHospital(
                        1L,
                        "hospName",
                        "number",
                        "address",
                        "oper"
                )
        );
    }
}