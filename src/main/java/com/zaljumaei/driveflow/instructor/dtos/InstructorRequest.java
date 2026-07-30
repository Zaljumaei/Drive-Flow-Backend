package com.zaljumaei.driveflow.instructor.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Simple Request for instructor, which contain few data.
 */
public record InstructorRequest(
         @NotBlank String firstName,
         @NotBlank String lastName,
         @NotBlank @Email String email
)
{ }
