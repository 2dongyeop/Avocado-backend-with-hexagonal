package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
}
