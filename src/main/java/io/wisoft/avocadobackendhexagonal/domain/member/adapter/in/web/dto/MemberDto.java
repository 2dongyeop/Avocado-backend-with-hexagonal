package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web.dto;

public record MemberDto(
        Long id,
        String email
) {
    public MemberDto(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
