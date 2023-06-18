package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.service.SignUpService;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.CustomNotFoundException;
import io.wisoft.avocadobackendhexagonal.settings.common.ServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class DeleteStaffServiceTest extends ServiceTest {

    @Autowired private SignUpService signUpService;
    @Autowired private LoadStaffPort loadStaffPort;
    @Autowired private SaveHospitalPort saveHospitalPort;
    @Autowired private DeleteStaffService deleteStaffService;

    @Test
    @DisplayName("삭제된 회원을 조회할 시 404 예외가 발생해야 한다.")
    public void delete_success() throws Exception {

        //given -- 조건
        //병원 생성 및 저장
        final Hospital hospital = getHospital();
        final Long savedHospitalId = saveHospitalPort.save(hospital);

        //의료진 가입 및 저장
        final var signupStaffCommand = getSignupStaffCommand(savedHospitalId);
        final Long savedStaffId = signUpService.signupStaff(signupStaffCommand);

        //when -- 동작
        deleteStaffService.delete(savedStaffId);

        //then -- 검증
        Assertions.assertThrows(CustomNotFoundException.class, () -> {
            loadStaffPort.findById(savedStaffId);
        });
    }

    private SignupStaffCommand getSignupStaffCommand(final Long savedHospitalId) {
        return new SignupStaffCommand(
                "name",
                "email",
                "password",
                "license",
                "DENTAL",
                savedHospitalId
        );
    }

    private Hospital getHospital() {
        return Hospital.registerHospital(
                null,
                "name",
                "number",
                "address",
                "oper"
        );
    }
}