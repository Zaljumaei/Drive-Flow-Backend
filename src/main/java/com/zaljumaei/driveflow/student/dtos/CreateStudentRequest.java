package com.zaljumaei.driveflow.student.dtos;

import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.licenseclass.LicenseClass;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;

@Builder
public record CreateStudentRequest (
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String phoneNumber,
        @NotNull LocalDate birthDate,
        @NotNull Address address,
        Set<LicenseClass> licenseClass
){ }
