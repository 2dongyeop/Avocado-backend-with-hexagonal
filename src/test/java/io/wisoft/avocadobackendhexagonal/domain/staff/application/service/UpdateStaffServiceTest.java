package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.service.SignUpService;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.command.UpdateStaffPasswordCommand;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.settings.common.ServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;


class UpdateStaffServiceTest extends ServiceTest {

    @Autowired private SignUpService signUpService;
    @Autowired private SaveHospitalPort saveHospitalPort;
    @Autowired private LoadStaffPort loadStaffPort;
    @Autowired private UpdateStaffService updateStaffService;

    @Test
    public void validatePassword_success() throws Exception {
        //given -- 조건
        //병원 생성 및 저장
        final Hospital hospital = getHospital();
        final Long savedHospitalId = saveHospitalPort.save(hospital);

        //의료진 가입 및 저장
        final var signupStaffCommand = getSignupStaffCommand(savedHospitalId);
        final Long savedStaffId = signUpService.signupStaff(signupStaffCommand);

        //비밀번호 세팅
        final String newPassword = "newPassword";
        final var updateStaffPasswordCommand = new UpdateStaffPasswordCommand(signupStaffCommand.password(), newPassword);

        //when -- 동작
        final Long updatedStaffId = updateStaffService.updatePassword(savedStaffId, updateStaffPasswordCommand);

        //then -- 검증
        final Staff findStaff = loadStaffPort.findById(updatedStaffId);
        Assertions.assertThat(BCrypt.checkpw(newPassword, findStaff.getPassword())).isTrue();
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