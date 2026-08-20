package com.zaljumaei.driveflow.student.enrollment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDate;

/**
 * Dto to hold the data for registration new student.
 */
@Builder
public record StudentEnrollmentRequest(
        @NotBlank String studentId,
        @NotBlank String licenseClassId,
        String studentEnrollmentStatus,
        LocalDate startDate,
        String notes
) { }