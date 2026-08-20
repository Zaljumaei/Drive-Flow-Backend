package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link DrivingSchoolController}.
 */
@WebMvcTest(DrivingSchoolController.class)
class DrivingSchoolControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DrivingSchoolService drivingSchoolService;

    private DrivingSchoolResponse drivingSchoolResponse;
    private DrivingSchoolRequest drivingSchoolRequest;
    private String id;
    private static final String BASE_URL = "/api/driving-schools";
    @Autowired
    private JsonMapper jsonMapper;

    @BeforeEach
    void setUp() {
        id = "1";
        Address address = Address.builder()
                .city("Bochum")
                .street("Hauptstrasse")
                .streetNumber("3")
                .zip("44799")
                .state("NRW")
                .build();

        drivingSchoolRequest  = new DrivingSchoolRequest(
                "Bochum-Fahrschule", "01234567",address);
        drivingSchoolResponse = new DrivingSchoolResponse(
                id, "Bochum-Fahrschule", "01234567");

    }

   @Test
   @DisplayName("Update method should return DrivingSchoolResponse from service")
    void updateShouldReturnResponseFromService() throws Exception {
        when(drivingSchoolService.update(eq(id), any(DrivingSchoolRequest.class)))
                .thenReturn(drivingSchoolResponse);

        mockMvc.perform(put(BASE_URL+ "/edit/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(drivingSchoolRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Bochum-Fahrschule"));

        verify(drivingSchoolService).update(eq(id), any(DrivingSchoolRequest.class));
   }

}