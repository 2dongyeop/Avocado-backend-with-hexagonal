package io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;

import java.util.List;

public interface LoadStaffUseCase {

    Staff loadStaff(final Long staffId);
    List<Staff> loadStaffList();
}
