package io.wisoft.avocadobackendhexagonal.domain.member.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence.BoardEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<BoardEntity> boardList = new ArrayList<>();

    public MemberEntity(final Long id, final String email, final String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
