package io.wisoft.avocadobackendhexagonal.domain.staff.application.service;

import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.in.LoadStaffUseCase;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoadStaffService implements LoadStaffUseCase {

    private final LoadStaffPort loadStaffPort;

    @Override
    public Staff loadStaff(final Long staffId) {
        return loadStaffPort.findById(staffId);
    }

    @Override
    public List<Staff> loadStaffList() {
        return loadStaffPort.findAll();
    }
}
