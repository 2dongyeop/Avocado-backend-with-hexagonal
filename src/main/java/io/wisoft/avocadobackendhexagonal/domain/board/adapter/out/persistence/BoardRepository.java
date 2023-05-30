package io.wisoft.avocadobackendhexagonal.domain.board.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
