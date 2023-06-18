package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.service.SignUpService;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.CustomNotFoundException;
import io.wisoft.avocadobackendhexagonal.settings.common.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LoadStaffServiceTest extends ServiceTest {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private SaveHospitalPort saveHospitalPort;
    @Autowired
    private LoadStaffService loadStaffService;

    @Test
    @DisplayName("의료진 단건 조회 성공 테스트")
    public void loadStaff_success() throws Exception {
        //given -- 조건
        //병원 생성 및 저장
        final Hospital hospital = getHospital();
        final Long savedHospitalId = saveHospitalPort.save(hospital);

        //의료진 가입 및 저장
        final var signupStaffCommand = getSignupStaffCommand(savedHospitalId);
        final Long savedStaffId = signUpService.signupStaff(signupStaffCommand);

        //when -- 동작
        final Staff findStaff = loadStaffService.loadStaff(savedStaffId);

        //then -- 검증
        assertThat(findStaff).isNotNull();
        assertThat(findStaff.getEmail()).isEqualTo(signupStaffCommand.email());
    }


    @Test
    @DisplayName("의료진 단건 조회 실패 테스트")
    public void loadStaff_fail() throws Exception {
        //given -- 조건
        //병원 생성 및 저장
        final Hospital hospital = getHospital();
        final Long savedHospitalId = saveHospitalPort.save(hospital);

        //의료진 가입 및 저장
        final var signupStaffCommand = getSignupStaffCommand(savedHospitalId);
        signUpService.signupStaff(signupStaffCommand);

        //when -- 동작
        //then -- 검증
        assertThrows(CustomNotFoundException.class, () -> {

            //존재하지 않는 id로 조회
            final Staff findStaff = loadStaffService.loadStaff(10000L);
        });
    }


    @Test
    @DisplayName("의료진 목록 조회 성공 테스트")
    public void loadStaffList_success() throws Exception {
        //given -- 조건
        //병원 생성 및 저장
        final Hospital hospital = getHospital();
        final Long savedHospitalId = saveHospitalPort.save(hospital);

        //30명의 의료진 저장
        IntStream.range(0, 30)
                .mapToObj(i -> signUpService.signupStaff(
                        new SignupStaffCommand(
                                "name " + i,
                                "email " + i,
                                "password " + i,
                                "license " + i,
                                "DENTAL",
                                savedHospitalId
                        )))
                .toList();

        //when -- 동작
        final List<Staff> staffList = loadStaffService.loadStaffList();

        //then -- 검증
        assertThat(staffList.size()).isEqualTo(30);
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
}