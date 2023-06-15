package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto.UpdateBoardRequest;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.UpdateBoardUseCase;
import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command.UpdateBoardCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class UpdateBoardController {

    private final UpdateBoardUseCase updateBoardUseCase;

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateBoard(
            @PathVariable final Long id,
            @RequestBody final UpdateBoardRequest request) {

        final UpdateBoardCommand command = getCommand(request);
        updateBoardUseCase.update(command);

        return ResponseEntity.ok().build();
    }

    private UpdateBoardCommand getCommand(final UpdateBoardRequest request) {
        return new UpdateBoardCommand(
                request.boardId(),
                request.body()
        );
    }
}
