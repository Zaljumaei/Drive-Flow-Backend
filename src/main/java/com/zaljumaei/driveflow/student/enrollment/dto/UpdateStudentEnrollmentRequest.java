package com.zaljumaei.driveflow.student.enrollment.dto;

import java.time.LocalDate;

/**
 * Dto to hold data for updating existed StudentEnrollment,
 * to allow student change his enrollment, like changing the driver license class.
 */
public record UpdateStudentEnrollmentRequest(
        String licenseClassId,
        String studentEnrollmentStatus,
        LocalDate startDate,
        String notes
) { }
