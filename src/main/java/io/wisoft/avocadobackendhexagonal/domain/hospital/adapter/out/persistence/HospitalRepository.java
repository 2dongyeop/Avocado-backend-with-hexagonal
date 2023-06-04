package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<HospitalEntity, Long> {

    boolean existsByName(final String name);
    Optional<HospitalEntity> findByName(final String name);
}
