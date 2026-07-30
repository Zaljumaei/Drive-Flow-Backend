package com.zaljumaei.driveflow.instructor.dtos;

import com.zaljumaei.driveflow.common.Address;

import java.time.LocalDate;

/**
 * Dto to hold new data for updating existed {@link com.zaljumaei.driveflow.instructor.domain.Instructor}
 * only provided data will be updated so no validator such NotNull or NotBlank is needed.
 */
public record UpdateInstructorRequest (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate,
        Address address
){ }
