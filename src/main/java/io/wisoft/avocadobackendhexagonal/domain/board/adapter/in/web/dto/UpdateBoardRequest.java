package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBoardRequest(@NotNull Long boardId, @NotBlank String body) {
}
