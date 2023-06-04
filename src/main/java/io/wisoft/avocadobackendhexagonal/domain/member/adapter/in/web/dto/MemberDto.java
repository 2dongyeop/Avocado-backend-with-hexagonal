package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web.dto;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto.BoardDto;
import io.wisoft.avocadobackendhexagonal.domain.board.domain.Board;

import java.util.List;

public record MemberDto(
        Long id,
        String email,
        String nickname,
        String phoneNumber,
        String memberPhotoPath,
        List<BoardDto> boards
) {
    public MemberDto(
            final Long id,
            final String email,
            final String nickname,
            final String phoneNumber,
            final String memberPhotoPath,
            final List<BoardDto> boards) {

        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.memberPhotoPath = memberPhotoPath;
        this.boards = boards;
    }
}
