package io.wisoft.avocadobackendhexagonal.domain.hospital.application.service;

import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.RegisterHospitalUseCase;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.command.RegisterHospitalCommand;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.out.SaveHospitalPort;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RegisterHospitalService implements RegisterHospitalUseCase {

    private final SaveHospitalPort saveHospitalPort;

    @Override
    public Long registerHospital(final RegisterHospitalCommand command) {

        final Hospital hospital = Hospital.registerHospital(
                null,
                command.name(),
                command.number(),
                command.address(),
                command.operatingTime()
        );

        return saveHospitalPort.save(hospital);
    }
}
