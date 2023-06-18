package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.out.SaveStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.LoadHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.UpdateStaffUseCase;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.command.UpdateStaffPasswordCommand;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.config.bcrypt.EncryptHelper;
import io.wisoft.avocadobackendhexagonal.global.exception.ErrorCode;
import io.wisoft.avocadobackendhexagonal.global.exception.Illegal.CustomIllegalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateStaffService implements UpdateStaffUseCase {

    private final EncryptHelper encryptHelper;
    private final LoadStaffPort loadStaffPort;
    private final SaveStaffPort saveStaffPort;
    private final LoadHospitalPort loadHospitalPort;

    @Override
    @Transactional
    public Long updateStaff(final Long staffId, final String hospitalName, final String photoPath) {

        final Staff staff = loadStaffPort.findById(staffId);

        if (StringUtils.hasText(hospitalName)) {
            staff.updateHospital(loadHospitalPort.findByName(hospitalName));
        }

        if (StringUtils.hasText(photoPath)) {
            staff.updatePhotoPath(photoPath);
        }

        return saveStaffPort.save(staff);
    }

    @Override
    @Transactional
    public Long updatePassword(final Long staffId, final UpdateStaffPasswordCommand command) {

        final Staff staff = loadStaffPort.findById(staffId);
        validatePassword(staff.getPassword(), command.oldPassword());

        staff.updatePassword(encryptHelper.encrypt(command.newPassword()));
        return saveStaffPort.save(staff);
    }

    private void validatePassword(final String password, final String oldPassword) {
        if (!encryptHelper.isMatch(oldPassword, password)) {
            throw new CustomIllegalException("password is invalid", ErrorCode.INVALID_PASSWORD);
        }
    }
}
