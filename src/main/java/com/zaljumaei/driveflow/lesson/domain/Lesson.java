package com.zaljumaei.driveflow.lesson.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.*;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.instructor.domain.Instructor;

/**
 * Abstract parent class for theory and practical lesson.
 * Any lesson is done by one instructor,
 * so we mapped the instructor to this class and not to Attendance.
 */
@MappedSuperclass
@Getter
@Setter
public abstract class Lesson extends TenantScopedEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    private LocalDate lessonDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private int durationMinutes;

    private String notes;

}
