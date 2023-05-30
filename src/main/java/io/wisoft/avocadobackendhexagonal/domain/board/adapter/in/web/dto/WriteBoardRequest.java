package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WriteBoardRequest(
        @NotNull Long memberId,
        @NotBlank String title,
        @NotBlank String body
) {
}
