package com.zaljumaei.driveflow.lesson.dto.TheoryLesson;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
public record TheoryLessonResponse (
        String id,

        String topicId,
        String topicTitle,

        String instructorId,
        String instructorName,

        LocalDate lessonDate,
        LocalTime startTime,
        LocalTime endTime,
        int durationMinutes,
        String notes

) { }
