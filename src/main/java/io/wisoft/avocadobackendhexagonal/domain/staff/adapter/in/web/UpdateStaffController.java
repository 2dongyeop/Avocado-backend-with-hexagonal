package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web.dto.UpdateStaffPasswordRequest;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.UpdateStaffUseCase;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.command.UpdateStaffPasswordCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class UpdateStaffController {

    private final UpdateStaffUseCase updateStaffUseCase;

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updateStaffPassword(
            @PathVariable("id") final Long id,
            @RequestBody @Valid final UpdateStaffPasswordRequest request) {

        final UpdateStaffPasswordCommand command = getUpdateStaffPasswordCommand(request);
        updateStaffUseCase.updatePassword(id, command);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStaff(
            @PathVariable("id") final Long id,
            @RequestParam(value = "hospitalName", required = false) final String hospitalName,
            @RequestParam(value = "photoPath", required = false) final String photoPath) {

        updateStaffUseCase.updateStaff(id, hospitalName, photoPath);
        return ResponseEntity.ok().build();
    }

    private UpdateStaffPasswordCommand getUpdateStaffPasswordCommand(final UpdateStaffPasswordRequest request) {
        return new UpdateStaffPasswordCommand(
                request.oldPassword(),
                request.newPassword()
        );
    }
}
