package com.zaljumaei.driveflow.student.dtos;

import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

/**
 * Simple Request for student, which contain few data.
 */
public record StudentRequest (
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        Set<LicenseClass> licenseClass
) { }
