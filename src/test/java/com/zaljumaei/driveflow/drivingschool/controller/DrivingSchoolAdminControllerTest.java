package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;


import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link DrivingSchoolAdminController}.
 * It doesn't test the logic of the controller methods,
 * rather, it tests request mapping, HTTP status codes, JSON responses,
 * request deserialization, and delegation to the service layer.
 */
@WebMvcTest(DrivingSchoolAdminController.class)
class DrivingSchoolAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DrivingSchoolService drivingSchoolService;

    private DrivingSchoolResponse drivingSchoolResponse;
    private DrivingSchoolRequest drivingSchoolRequest;
    private String id;

    @Autowired
    private JsonMapper jsonMapper;

    private static final String BASE_URL = "/api/admin/driving-schools";

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
    void processShouldReturnResponseFromService() throws Exception {
        when(drivingSchoolService.create(any(DrivingSchoolRequest.class)))
                .thenReturn(drivingSchoolResponse);

        mockMvc.perform(post(BASE_URL +"/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(jsonMapper.writeValueAsString(drivingSchoolRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Bochum-Fahrschule"));

        verify(drivingSchoolService).create(any(DrivingSchoolRequest.class));

    }

    @Test
    void getByIdShouldReturnResponseFromService() throws Exception {
        when(drivingSchoolService.findById(id)).thenReturn(drivingSchoolResponse);

        mockMvc.perform(get(BASE_URL + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bochum-Fahrschule"));

        verify(drivingSchoolService).findById(id);
    }

    @Test
    void getByNameShouldReturnResponseFromService() throws Exception {
        when(drivingSchoolService.findByName(drivingSchoolRequest.name())).thenReturn(drivingSchoolResponse);

        mockMvc.perform(get(BASE_URL)
                        .param("name", drivingSchoolRequest.name())
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Bochum-Fahrschule"));

        verify(drivingSchoolService).findByName(drivingSchoolRequest.name());
    }

    @Test
    void getAllShouldReturnListOfResponseFromService() throws Exception {
        when(drivingSchoolService.findAllDrivingSchools()).thenReturn(List.of(drivingSchoolResponse));

        mockMvc.perform(get(BASE_URL + "/allDrSchools"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Bochum-Fahrschule"));

        verify(drivingSchoolService).findAllDrivingSchools();
    }

    @Test
    void deleteShouldDeleteSuccessfully() throws Exception {
        Mockito.doNothing().when(drivingSchoolService).delete(id);

        mockMvc.perform(delete(BASE_URL + "/delete/" + id))
                .andExpect(status().isNoContent());

        verify(drivingSchoolService).delete(id);
    }

}