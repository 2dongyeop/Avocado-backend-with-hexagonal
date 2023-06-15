package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.UpdateMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class UpdateMemberController {

    private final UpdateMemberUseCase updateMemberUseCase;

    @PatchMapping("/{id}")
    public ResponseEntity<Long> updateMember(
            @PathVariable("id") final Long id,
            @RequestParam(value = "email", required = false) final String email) {

        return ResponseEntity.ok(updateMemberUseCase.updateMember(id, email));
    }
}
