package io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.command.RegisterHospitalCommand;

public interface RegisterHospitalUseCase {

    Long registerHospital(final RegisterHospitalCommand command);
}
