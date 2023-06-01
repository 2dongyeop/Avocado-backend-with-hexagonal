package io.wisoft.avocadobackendhexagonal.global.enumeration;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HospitalDept {
    DENTAL("치과"),
    OPHTHALMOLOGY("안과"),
    DERMATOLOGY("피부과"),
    PLASTIC_SURGERY("성형외과"),
    OBSTETRICS("산부인과"),
    PSYCHIATRY("정신건강의학과"),
    ORTHOPEDICS("정형외과"),
    NEUROSURGERY("신경외과"),
    SURGICAL("외과"),
    NEUROLOGY("신경과"),
    PEDIATRIC("소아과"),
    INTERNAL_MEDICINE("내과"),
    OTOLARYNGOLOGY("이비인후과"),
    UROLOGY("비뇨기과"),
    ORIENTAL_MEDICAL("한의원");

    private final String description;
}