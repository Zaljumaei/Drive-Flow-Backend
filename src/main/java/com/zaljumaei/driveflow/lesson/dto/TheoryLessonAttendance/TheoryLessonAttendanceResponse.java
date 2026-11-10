package com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance;

import com.zaljumaei.driveflow.lesson.domain.TheoryLessonStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TheoryLessonAttendanceResponse (
        String id,

        String theoryLessonId,
        String theoryTopicTitle,

        String studentEnrollmentId,
        String studentId,
        String studentFullName,

        LocalDateTime confirmedAt,
        TheoryLessonStatus status,

        String notes
){ }
