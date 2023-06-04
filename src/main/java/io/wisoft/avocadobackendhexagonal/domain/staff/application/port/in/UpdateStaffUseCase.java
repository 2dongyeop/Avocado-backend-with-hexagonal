package io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in;

import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.command.UpdateStaffPasswordCommand;

public interface UpdateStaffUseCase {

    Long updateStaff(final Long staffId, final String hospitalName, final String photoPath);
    Long updatePassword(final Long staffId, final UpdateStaffPasswordCommand command);
}
