package com.zaljumaei.driveflow.student.enrollment.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * Dto to hold start-, completed-, or canceledDate.
 */
public record EnrollmentDateRequest(
        @NotBlank String studentEnrollmentId,
        @NotBlank LocalDate date,
        String notes
) { }
