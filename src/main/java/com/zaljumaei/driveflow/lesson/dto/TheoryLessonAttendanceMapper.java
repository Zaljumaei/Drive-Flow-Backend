package com.zaljumaei.driveflow.lesson.dto;

import com.zaljumaei.driveflow.lesson.domain.TheoryLessonAttendance;
import com.zaljumaei.driveflow.student.enrollment.StudentEnrollmentService;
import com.zaljumaei.driveflow.student.enrollment.StudentLicenseEnrollment;

public class TheoryLessonAttendanceMapper {
    private StudentEnrollmentService studentEnrollmentService;

    public TheoryLessonAttendance toEntity(TheoryLessonAttendanceRequest request) {
        TheoryLessonAttendance attendance = new TheoryLessonAttendance();

        StudentLicenseEnrollment enrollment = studentEnrollmentService.findEntityById(request.studentEnrollmentId());

        attendance.setStudentLicenseEnrollment(enrollment);
        attendance.setStatus(request.status());
        attendance.setNotes(request.notes());
        attendance.setConfirmedAt(request.conformedAt());

        return attendance;
    }
}
