package com.zaljumaei.driveflow.instructor.dtos;

import com.zaljumaei.driveflow.common.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

/**
 * Dto to hold new data for creating new  instructor
 * this dto contain more date than {@link InstructorRequest},
 * which are needed by creating the instructor.
 */
@Builder
public record CreateInstructorRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String phoneNumber,
        @NotBlank LocalDate birthDate,
        @NotBlank Address address
)
{ }
