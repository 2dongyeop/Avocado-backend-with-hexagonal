package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupStaffRequest;
import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto.RegisterHospitalRequest;
import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.out.persistence.HospitalRepository;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffRepository;
import io.wisoft.avocadobackendhexagonal.settings.common.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LoadStaffControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private HospitalRepository hospitalRepository;

    @BeforeEach
    void clean() throws Exception {
        hospitalRepository.deleteAll();
        staffRepository.deleteAll();

        //병원 등록
        mockMvc.perform(post("/api/hospitals")
                        .contentType(APPLICATION_JSON)
                        .content(getHospitalRequestString()))
                .andExpect(status().isOk())
                .andDo(print());

        //의료진 가입
        mockMvc.perform(post("/api/auth/signup/staff")
                        .contentType(APPLICATION_JSON)
                        .content(getSignupStaffRequest()))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @DisplayName("의료진 단건 조회 성공 테스트")
    public void loadStaff_success() throws Exception {
        //expected
        mockMvc.perform(
                        get("/api/staff/{id}/details", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.email").value("email@email.com"))
                .andDo(print());
    }


    private RegisterHospitalRequest getHospitalRequest() {
        return new RegisterHospitalRequest(
                "name",
                "number",
                "address",
                "operatingTime"
        );
    }

    private String getHospitalRequestString() throws JsonProcessingException {
        final var hospitalRequest = getHospitalRequest();

        final String hospitalRequestString = objectMapper.writeValueAsString(hospitalRequest);
        return hospitalRequestString;
    }

    private String getSignupStaffRequest() throws JsonProcessingException {
        final var request = new SignupStaffRequest(
                "name",
                "email@email.com",
                "password123",
                "password123",
                "license",
                "DENTAL",
                1L
        );

        return objectMapper.writeValueAsString(request);
    }
}