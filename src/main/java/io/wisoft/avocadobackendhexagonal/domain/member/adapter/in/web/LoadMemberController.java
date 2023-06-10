package io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.in.web.dto.BoardDto;
import io.wisoft.avocadobackendhexagonal.domain.member.adapter.in.web.dto.MemberDto;
import io.wisoft.avocadobackendhexagonal.domain.member.application.port.in.LoadMemberUseCase;
import io.wisoft.avocadobackendhexagonal.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class LoadMemberController {

    private final LoadMemberUseCase loadMemberUseCase;

    @GetMapping("{id}/details")
    public ResponseEntity<MemberDto> member(@PathVariable("id") final Long id) {
        final Member member = loadMemberUseCase.loadMember(id);
        return ResponseEntity.ok(getMemberDto(member));
    }

    @GetMapping
    public List<MemberDto> members() {
        final List<Member> members = loadMemberUseCase.loadMemberList();
        return members.stream()
                .map(this::getMemberDto)
                .toList();
    }

    private MemberDto getMemberDto(final Member member) {
        return new MemberDto(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPhoneNumber(),
                member.getMemberPhotoPath(),

                member.getBoards().stream()
                        .map(BoardDto::new)
                        .toList()
        );
    }
}
