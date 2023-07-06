package io.wisoft.avocadobackendhexagonal.domain.boardreply.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardReplyRepository extends JpaRepository<BoardReplyEntity, Long> {
}
