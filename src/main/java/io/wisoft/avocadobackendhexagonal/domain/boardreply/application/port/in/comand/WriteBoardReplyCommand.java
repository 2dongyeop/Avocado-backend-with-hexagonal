package io.wisoft.avocadobackendhexagonal.domain.boardreply.application.port.in.comand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WriteBoardReplyCommand(
        @NotNull Long boardId,
        @NotNull Long staffId,
        @NotBlank String reply
) { }
