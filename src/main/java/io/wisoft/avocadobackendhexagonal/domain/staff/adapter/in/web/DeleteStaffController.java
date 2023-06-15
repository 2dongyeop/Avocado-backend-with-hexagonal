package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.DeleteStaffUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class DeleteStaffController {

    private final DeleteStaffUseCase deleteStaffUseCase;

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable("id") final Long id) {
        deleteStaffUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
