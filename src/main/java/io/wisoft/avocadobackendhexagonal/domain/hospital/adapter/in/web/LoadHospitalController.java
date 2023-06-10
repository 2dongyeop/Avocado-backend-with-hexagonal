package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto.HospitalDto;
import io.wisoft.avocadobackendhexagonal.domain.hospital.application.port.in.LoadHospitalUseCase;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/hospitals")
@RequiredArgsConstructor
public class LoadHospitalController {

    private final LoadHospitalUseCase loadHospitalUseCase;

    @GetMapping("/{id}/details")
    public ResponseEntity<HospitalDto> hospital(@PathVariable final Long id) {
        final Hospital hospital = loadHospitalUseCase.loadHospital(id);
        final HospitalDto hospitalDto = getHospitalDto(hospital);

        return ResponseEntity.ok(hospitalDto);
    }

    @GetMapping
    public ResponseEntity<List<HospitalDto>> hospitals() {
        final List<HospitalDto> hospitalDtoList = loadHospitalUseCase.loadHospitalList().stream()
                .map(hospital -> getHospitalDto(hospital))
                .toList();

        return ResponseEntity.ok(hospitalDtoList);
    }

    private HospitalDto getHospitalDto(final Hospital hospital) {
        return new HospitalDto(
                hospital.getId(),
                hospital.getName(),
                hospital.getNumber(),
                hospital.getAddress(),
                hospital.getOperatingTime()
        );
    }
}
