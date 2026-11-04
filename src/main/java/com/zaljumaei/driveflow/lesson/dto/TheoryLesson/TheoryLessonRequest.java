package com.zaljumaei.driveflow.lesson.dto.TheoryLesson;

import java.time.LocalDate;
import java.time.LocalTime;

public record TheoryLessonRequest (
        String instructorId,

        String topicId,

        LocalDate lessonDate,
        LocalTime startTime,
        LocalTime endTime,

        int durationMinutes,
        String notes
)
{ }
