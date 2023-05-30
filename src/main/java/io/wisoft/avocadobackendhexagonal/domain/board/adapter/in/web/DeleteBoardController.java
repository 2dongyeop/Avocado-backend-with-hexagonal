package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.DeleteBoardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeleteBoardController {

    private final DeleteBoardUseCase deleteBoardUseCase;

    @DeleteMapping("/api/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable final Long id) {

        deleteBoardUseCase.delete(id);
        return ResponseEntity.ok().build();
    }
}
