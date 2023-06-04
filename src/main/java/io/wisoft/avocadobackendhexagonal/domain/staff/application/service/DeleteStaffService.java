package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.DeleteStaffUseCase;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.DeleteStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteStaffService implements DeleteStaffUseCase {

    private final DeleteStaffPort deleteStaffPort;
    private final LoadStaffPort loadStaffPort;

    @Override
    @Transactional
    public void delete(final Long staffId) {
        deleteStaffPort.delete(loadStaffPort.findStaffEntityById(staffId));
    }
}
