package io.wisoft.avocadobackendhexagonal.domain.hospital.application.service;

import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.RegisterHospitalUseCase;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.command.RegisterHospitalCommand;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.LoadHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.global.exception.duplicate.DuplicateHospitalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterHospitalService implements RegisterHospitalUseCase {

    private final SaveHospitalPort saveHospitalPort;
    private final LoadHospitalPort loadHospitalPort;

    @Override
    @Transactional
    public Long registerHospital(final RegisterHospitalCommand command) {

        validateDuplicateHospitalName(command.name());

        final Hospital hospital = Hospital.registerHospital(
                null,
                command.name(),
                command.number(),
                command.address(),
                command.operatingTime()
        );

        return saveHospitalPort.save(hospital);
    }

    private void validateDuplicateHospitalName(final String name) {
        if(loadHospitalPort.findByExistsByName(name)) {
            log.error("중복된 이름의 병원이 이미 존재합니다.");
            throw new DuplicateHospitalException("중복된 이름의 병원이 이미 존재합니다.");
        }
    }
}
