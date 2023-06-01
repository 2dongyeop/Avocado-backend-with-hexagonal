package io.wisoft.avocadobackendhexagonal.domain.board.application.port.in.command;


public record BoardCommand(
        String title,
        String body,
        String boardPhotoPath,
        String dept,
        String writer
) { }
