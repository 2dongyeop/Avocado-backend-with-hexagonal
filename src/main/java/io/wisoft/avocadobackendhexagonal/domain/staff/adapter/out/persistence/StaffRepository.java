package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    boolean existsByEmail(final String email);

    Optional<StaffEntity> findByEmail(final String email);
}
