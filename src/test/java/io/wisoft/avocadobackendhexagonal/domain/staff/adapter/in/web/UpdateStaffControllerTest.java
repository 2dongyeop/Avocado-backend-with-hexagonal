package io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.avocadobackendhexagonal.domain.auth.adapter.in.web.dto.SignupStaffRequest;
import io.wisoft.avocadobackendhexagonal.domain.hospital.adapter.in.web.dto.RegisterHospitalRequest;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.in.web.dto.UpdateStaffPasswordRequest;
import io.wisoft.avocadobackendhexagonal.domain.staff.adapter.out.persistence.StaffRepository;
import io.wisoft.avocadobackendhexagonal.settings.common.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UpdateStaffControllerTest extends ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private StaffRepository staffRepository;

    @BeforeEach
    void clean() throws Exception {
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
    public void updateStaffPassword() throws Exception {
        //given
        final var request = getUpdatePasswordRequest();

        //expected
        mockMvc.perform(
                        patch("/api/staff/{id}/password", 1L)
                                .contentType(APPLICATION_JSON)
                                .content(request))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private String getUpdatePasswordRequest() throws JsonProcessingException {
        final var request = new UpdateStaffPasswordRequest(
                "password123", "updatePassword123"
        );

        return objectMapper.writeValueAsString(request);
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

        return objectMapper.writeValueAsString(hospitalRequest);
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