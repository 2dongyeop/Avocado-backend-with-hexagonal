package io.wisoft.avocadobackendhexagonal.domain.auth.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.SignupUseCase;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupMemberCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.in.command.SignupStaffCommand;
import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.out.SaveStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.LoadHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.out.SaveMemberPort;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignupUseCase {

    private final SaveMemberPort saveMemberPort;
    private final SaveStaffPort saveStaffPort;
    private final LoadHospitalPort loadHospitalPort;

    @Override
    public Long signupMember(final SignupMemberCommand request) {

        final Member member = Member.createMember(
                null,
                request.email(),
                request.nickname(),
                request.password(),
                request.phoneNumber(),
                request.memberPhotoPath()
        );

        return saveMemberPort.save(member);
    }

    @Override
    public Long signupStaff(final SignupStaffCommand request) {

        final Hospital hospital = loadHospitalPort.findById(request.hospitalId());

        final Staff staff = Staff.createStaff(
                null,
                request.name(),
                request.email(),
                request.password(),
                request.license_path(),
                HospitalDept.valueOf(request.dept()),
                hospital
        );

        return saveStaffPort.save(staff);
    }
}
