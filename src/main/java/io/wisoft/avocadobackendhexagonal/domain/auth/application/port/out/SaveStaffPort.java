package io.wisoft.avocadobackendhexagonal.domain.auth.application.port.out;

import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;

public interface SaveStaffPort {

    Long save(final Staff staff);
}
