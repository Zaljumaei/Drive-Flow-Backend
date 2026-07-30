package com.zaljumaei.driveflow.instructor.dtos;

import lombok.Builder;

@Builder
public record InstructorResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) { }
