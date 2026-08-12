package com.zaljumaei.driveflow.student.dtos;

import lombok.Builder;

@Builder
public record StudentResponse(
        String id,
        String firstName,
        String lastName,
        String email
) { }
