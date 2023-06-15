package io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;

import java.util.List;

public interface LoadStaffPort {

    Staff findById(final Long staffId);
    StaffEntity findStaffEntityById(final Long staffId);

    List<Staff> findAll();

    boolean existsByEmail(final String email);

    Staff findByEmail(final String email);
}
