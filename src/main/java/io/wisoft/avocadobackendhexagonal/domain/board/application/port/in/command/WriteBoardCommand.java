package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WriteBoardCommand(
        @NotNull Long memberId,
        @NotBlank String title,
        @NotBlank String body) {
}
