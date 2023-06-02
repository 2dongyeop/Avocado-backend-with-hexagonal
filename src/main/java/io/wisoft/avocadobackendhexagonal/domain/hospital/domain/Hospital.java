package io.wisoft.avocadobackendhexagonal.domain.hospital.domain;

import io.wisoft.avocadobackendhexagonal.global.basetime.BaseTimeDomain;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital extends BaseTimeDomain {
    private Long id;
    private String name;
    private String number;
    private String address;
    private String operatingTime;


    public static Hospital registerHospital(
            final Long id,
            final String name,
            final String number,
            final String address,
            final String operatingTime) {

        final Hospital hospital = new Hospital();
        hospital.id = id;
        hospital.name = name;
        hospital.number = number;
        hospital.address = address;
        hospital.operatingTime = operatingTime;
        hospital.createDomain();

        return hospital;
    }
}
