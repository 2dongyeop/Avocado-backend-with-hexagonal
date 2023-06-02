package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto.WriteBoardRequest;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.WriteBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.WriteBoardCommand;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WriteBoardController {

    private final WriteBoardUseCase writeBoardUseCase;

    @PostMapping("/api/boards")
    public ResponseEntity<Long> writeBoard(@RequestBody @Valid final WriteBoardRequest request) {

        final WriteBoardCommand writeBoardCommand = getCommand(request);

        return ResponseEntity.ok(writeBoardUseCase.writeBoard(writeBoardCommand));
    }

    private WriteBoardCommand getCommand(final WriteBoardRequest request) {
        return new WriteBoardCommand(request.memberId(), request.title(), request.body(), request.boardPhotoPath(), request.dept());
    }
}
