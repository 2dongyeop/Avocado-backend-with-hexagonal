package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.out.SaveStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffMapper;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffRepository;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaffPersistenceAdapter implements SaveStaffPort {

    private final StaffRepository staffRepository;

    @Override
    public Long save(final Staff staff) {
        return staffRepository.save(StaffMapper.staffToStaffEntity(staff)).getId();
    }
}
