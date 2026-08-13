package com.zaljumaei.driveflow.student.dtos;

import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;

import jakarta.validation.constraints.Email;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record UpdateStudentRequest(
        String firstName,
        String lastName,
        @Email String email,
        String phoneNumber,
        LocalDate birthDate,
        Address address,
        Set<LicenseClass> licenseClass
) {
}
