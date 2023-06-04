package io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;

public interface DeleteStaffPort {

    void delete(final StaffEntity staffEntity);
}
