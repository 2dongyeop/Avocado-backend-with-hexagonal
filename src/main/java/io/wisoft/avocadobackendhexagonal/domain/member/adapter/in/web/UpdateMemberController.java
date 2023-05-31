package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.UpdateMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateMemberController {

    private final UpdateMemberUseCase updateMemberUseCase;

    @PatchMapping("/api/members/{id}")
    public ResponseEntity<Long> updateMember(
            @PathVariable("id") final Long id,
            @RequestParam(value = "email", required = false) final String email) {

        return ResponseEntity.ok(updateMemberUseCase.updateMember(id, email));
    }
}
