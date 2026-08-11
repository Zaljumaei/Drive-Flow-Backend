package com.zaljumaei.driveflow.instructor.controller;

import com.github.javafaker.Faker;
import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.instructor.dtos.CreateInstructorRequest;
import com.zaljumaei.driveflow.instructor.dtos.InstructorResponse;
import com.zaljumaei.driveflow.instructor.service.InstructorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.json.JsonMapper;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InstructorController.class)
class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private InstructorService instructorService;

    private CreateInstructorRequest createInstructorRequest;
    private InstructorResponse instructorResponse;

    private Faker faker;

    private String BASE_URL = "/api/instructor";
    @Autowired
    private JsonMapper jsonMapper;

    @BeforeEach
    void setUp() {

        faker = new Faker(Locale.GERMANY);

        Address adress = Address.builder()
                .state(faker.address().state())
                .city(faker.address().city())
                .streetNumber(faker.address().streetAddressNumber())
                .street(faker.address().streetName())
                .build();

        createInstructorRequest = CreateInstructorRequest.builder()
                .address(adress)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

        instructorResponse = InstructorResponse.builder()
                .id(faker.idNumber().toString())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .build();

    }

    @Test
    void processInstructorRequest() throws Exception {
        when(instructorService.create(any(CreateInstructorRequest.class)))
                .thenReturn(instructorResponse);

        mockMvc.perform(post(BASE_URL+ "/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header("X-Tenant-Id", "1")
                        .content(jsonMapper.writeValueAsString(createInstructorRequest))
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value(instructorResponse.email()));
    }
}