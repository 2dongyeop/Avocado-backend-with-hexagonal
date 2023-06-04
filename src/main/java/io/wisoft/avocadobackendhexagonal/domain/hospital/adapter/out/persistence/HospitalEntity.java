package io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence;

import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffEntity;
import io.wisoft.avocadobackendhexagonal.global.basetime.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HospitalEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hosp_id")
    private Long id;

    @Column(name = "hosp_name", unique = true, nullable = false)
    private String name;

    @Column(name = "hosp_number", nullable = false)
    private String number;

    @Column(name = "hosp_address", nullable = false)
    private String address;

    @Column(name = "hosp_operatingtime", columnDefinition="TEXT")
    private String operatingTime;

    @OneToMany(mappedBy = "hospital")
    private final List<StaffEntity> staffList = new ArrayList<>();

    public static HospitalEntity createHospital(
            final Long id,
            final String name,
            final String number,
            final String address,
            final String operatingTime) {

        final HospitalEntity hospital = new HospitalEntity();
        hospital.id = id;
        hospital.name = name;
        hospital.number = number;
        hospital.address = address;
        hospital.operatingTime = operatingTime;

        return hospital;
    }
}
