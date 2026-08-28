package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.zaljumaei.driveflow.student.enrollment.StudentLicenseEnrollment;
import com.zaljumaei.driveflow.vehicle.domain.Vehicle;

/**
 * Entity class for practical lessons.
 * Since this lesson is just need a student and an instructor,
 * we don't need to use extra entity to store more information like the case by theory lessons.
 */
@Entity
@Getter
@Setter
public class PracticalLesson extends Lesson {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private StudentLicenseEnrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    private PracticalLessonStatus status;
}
