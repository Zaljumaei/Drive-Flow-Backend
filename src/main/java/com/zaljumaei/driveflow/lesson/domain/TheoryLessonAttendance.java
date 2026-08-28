package com.zaljumaei.driveflow.lesson.domain;

import com.zaljumaei.driveflow.student.enrollment.StudentLicenseEnrollment;
import jakarta.persistence.*;
import com.zaljumaei.driveflow.common.TenantScopedEntity;

import java.time.LocalDateTime;

/**
 * Class to manage student and the status for TheoryLesson.
 */
@Entity
public class TheoryLessonAttendance extends TenantScopedEntity {

    @ManyToOne
    @JoinColumn(name = "student_license_enrollment_id")
    private StudentLicenseEnrollment studentLicenseEnrollment;

    @ManyToOne
    @JoinColumn(name = "theory_lesson_id")
    private TheoryLesson  theoryLesson;

    private TheoryLessonStatus status;

    private LocalDateTime confirmedAt;

    private String notes;

}
