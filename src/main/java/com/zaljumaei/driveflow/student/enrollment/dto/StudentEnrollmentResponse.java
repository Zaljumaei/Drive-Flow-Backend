package com.zaljumaei.driveflow.student.enrollment.dto;

import lombok.Builder;

import java.time.LocalDate;

/**
 * Dto for holding the most important information about enrolment of student.
 */
@Builder
public record StudentEnrollmentResponse(
        String enrollmentId,
        String licenseClassCode,
        String enrollmentStatus,
        LocalDate registrationDate,
        LocalDate startDate
) { }
