package com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance;

import com.zaljumaei.driveflow.lesson.domain.TheoryLessonStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TheoryLessonAttendanceRequest (
        @NotBlank
        String theoryLessonId,
        @NotBlank
        String studentEnrollmentId,
        @NotNull
        LocalDateTime conformedAt,
        @NotNull
        TheoryLessonStatus status,
        @NotBlank
        String notes
){ }
