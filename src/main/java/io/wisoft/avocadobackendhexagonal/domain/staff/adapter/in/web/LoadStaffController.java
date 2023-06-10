package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web.dto.StaffDto;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.LoadStaffUseCase;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class LoadStaffController {

    private final LoadStaffUseCase loadStaffUseCase;

    @GetMapping("/{id}/details")
    public ResponseEntity<StaffDto> staff(@PathVariable("id") final Long id) {

        return ResponseEntity.ok(getStaffDto(loadStaffUseCase.loadStaff(id)));
    }

    @GetMapping
    public ResponseEntity<List<StaffDto>> staffs() {
        return ResponseEntity.ok(
                loadStaffUseCase.loadStaffList().stream()
                        .map(this::getStaffDto)
                        .toList()
        );
    }

    private StaffDto getStaffDto(final Staff staff) {
        return new StaffDto(
                staff.getId(),
                staff.getName(),
                staff.getEmail(),
                staff.getLicense_path(),
                staff.getDept().name(),
                staff.getHospital().getName()
        );
    }
}
