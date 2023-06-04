package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalEntity;
import io.wisoft.avocadobackendhexagonal.global.enumeration.HospitalDept;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StaffEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long id;

    @Column(name = "staff_name", nullable = false)
    private String name;

    @Column(name = "staff_email", unique = true, nullable = false)
    private String email;

    @Column(name = "staff_password", nullable = false)
    private String password;

    @Column(name = "staff_license_path", nullable = false)
    private String license_path;

    @Enumerated(EnumType.STRING)
    @Column(name = "dept", nullable = false)
    private HospitalDept dept;

    @Column(name = "staff_photo_path")
    private String staffPhotoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hosp_id")
    private HospitalEntity hospital;

    /* 연관관계 메서드 */
    public void setHospital(final HospitalEntity hospital) {
        //comment: 기존 관계 제거
        if (this.hospital != null) {
            this.hospital.getStaffList().remove(this);
        }

        this.hospital = hospital;

        //comment: 무한루프 방지
        if (!hospital.getStaffList().contains(this)) {
            hospital.getStaffList().add(this);
        }
    }

    public static StaffEntity createStaff(
            final Long id,
            final String name,
            final String email,
            final String password,
            final String license_path,
            final String staffPhotoPath,
            final HospitalDept dept,
            final HospitalEntity hospital
    ) {
        final StaffEntity staffEntity = new StaffEntity();
        staffEntity.id = id;
        staffEntity.name = name;
        staffEntity.email = email;
        staffEntity.password = password;
        staffEntity.license_path = license_path;
        staffEntity.staffPhotoPath = staffPhotoPath;
        staffEntity.dept = dept;
        staffEntity.setHospital(hospital);

        return staffEntity;
    }
}
