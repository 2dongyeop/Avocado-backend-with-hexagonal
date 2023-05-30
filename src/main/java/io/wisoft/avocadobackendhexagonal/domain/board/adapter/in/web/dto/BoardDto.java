package io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto;

public record BoardDto(
        String title,
        String body,
        String writer
) {

    public BoardDto {
    }
}
