package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.adapter;

import io.wisoft.avocadobackendhexagonal.domain.auth.application.port.out.SaveStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffMapper;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffRepository;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.DeleteStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.application.port.out.LoadStaffPort;
import io.wisoft.avocadobackendhexagonal.domain.staff.domain.Staff;
import io.wisoft.avocadobackendhexagonal.global.exception.notfound.NotFoundStaffException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StaffPersistenceAdapter implements SaveStaffPort, LoadStaffPort, DeleteStaffPort {

    private final StaffRepository staffRepository;

    @Override
    public Long save(final Staff staff) {
        return staffRepository.save(StaffMapper.staffToStaffEntity(staff)).getId();
    }

    @Override
    public Staff findById(final Long staffId) {
        final StaffEntity staffEntity = staffRepository.findById(staffId).orElseThrow(NotFoundStaffException::new);
        return StaffMapper.staffEntityToStaff(staffEntity);
    }

    @Override
    public StaffEntity findStaffEntityById(final Long staffId) {
        return staffRepository.findById(staffId)
                .orElseThrow(NotFoundStaffException::new);
    }

    @Override
    public List<Staff> findAll() {
        return staffRepository.findAll().stream()
                .map(staffEntity
                        -> StaffMapper.staffEntityToStaff(staffEntity))
                .toList();
    }

    @Override
    public boolean existsByEmail(final String email) {
        return staffRepository.existsByEmail(email);
    }

    @Override
    public void delete(final StaffEntity staffEntity) {
        staffRepository.delete(staffEntity);
    }
}
