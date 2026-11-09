package com.zaljumaei.driveflow.lesson.dto;

import com.zaljumaei.driveflow.lesson.domain.TheoryLessonStatus;

import java.time.LocalDateTime;

public record TheoryLessonAttendanceRequest (
        String theoryLessonId,
        String studentEnrollmentId,
        LocalDateTime conformedAt,
        TheoryLessonStatus status,
        String notes
){ }
