package io.wisoft.avocadobackendhexagonal.domain.staff.domain;

import io.wisoft.avocadobackendhexagonal.domain.boardreply.domain.BoardReply;
import io.wisoft.avocadobackendhexagonal.domain.hospital.domain.Hospital;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String license_path;
    private HospitalDept dept;
    private String staffPhotoPath;
    private Hospital hospital;
    private List<BoardReply> boardReplies;

    public static Staff createStaff (
            final Long id,
            final String name,
            final String email,
            final String password,
            final String license_path,
            final String staffPhotoPath,
            final HospitalDept dept,
            final Hospital hospital) {

        final Staff staff = new Staff();
        staff.id = id;
        staff.name = name;
        staff.email = email;
        staff.password = password;
        staff.license_path = license_path;
        staff.staffPhotoPath = staffPhotoPath;
        staff.dept = dept;
        staff.hospital = hospital;

        return staff;
    }

    public void updateHospital(final Hospital hospital) {
        this.hospital = hospital;
    }

    public void updatePhotoPath(final String photoPath) {
        this.staffPhotoPath = photoPath;
    }

    public void updatePassword(final String newPassword) {
        this.password = newPassword;
    }
}
