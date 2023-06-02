package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto.RegisterHospitalRequest;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.RegisterHospitalUseCase;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.command.RegisterHospitalCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterHospitalController {
    
    private final RegisterHospitalUseCase useCase;

    @PostMapping("/api/hospitals")
    public ResponseEntity<Long> registerHospitalRequest(
            @RequestBody @Valid final RegisterHospitalRequest request) {

        final RegisterHospitalCommand command = getCommand(request);
        return ResponseEntity.ok(useCase.registerHospital(command));
    }

    private RegisterHospitalCommand getCommand(final RegisterHospitalRequest request) {
        return new RegisterHospitalCommand(
                request.name(),
                request.number(),
                request.address(),
                request.operatingTime()
        );
    }
}
