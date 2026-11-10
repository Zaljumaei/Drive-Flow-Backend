package com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance;

import com.zaljumaei.driveflow.lesson.service.TheoryLessonService;
import org.springframework.stereotype.Component;

import com.zaljumaei.driveflow.lesson.domain.TheoryLesson;
import com.zaljumaei.driveflow.lesson.domain.TheoryLessonAttendance;
import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.student.enrollment.StudentEnrollmentService;
import com.zaljumaei.driveflow.student.enrollment.StudentLicenseEnrollment;

@Component
public class TheoryLessonAttendanceMapper {

    private StudentEnrollmentService studentEnrollmentService;
    private TheoryLessonService theoryLessonService;

    public TheoryLessonAttendance toEntity(TheoryLessonAttendanceRequest request) {
        TheoryLessonAttendance attendance = new TheoryLessonAttendance();

        StudentLicenseEnrollment enrollment = studentEnrollmentService.findEntityById(request.studentEnrollmentId());

        attendance.setStudentLicenseEnrollment(enrollment);
        attendance.setStatus(request.status());
        attendance.setNotes(request.notes());
        attendance.setConfirmedAt(request.conformedAt());

        return attendance;
    }

    /**
     * Convert a TheoryLessonAttendance entity to a response DTO.
     *
     * @param attendance The theory lesson attendance entity.
     * @return The theory lesson attendance response.
     */
    public TheoryLessonAttendanceResponse toResponse(TheoryLessonAttendance attendance) {
        StudentLicenseEnrollment enrollment = attendance.getStudentLicenseEnrollment();

        Student student = enrollment.getStudent();
        TheoryLesson lesson = attendance.getTheoryLesson();

        return TheoryLessonAttendanceResponse.builder()
                .id(attendance.getId())
                .theoryLessonId(lesson.getId())
                .theoryTopicTitle(lesson.getTopic().getTitle())
                .studentEnrollmentId(enrollment.getId())
                .studentId(student.getId())
                .studentFullName(student.getPersonDetails().getFullname())
                .confirmedAt(attendance.getConfirmedAt())
                .status(attendance.getStatus())
                .notes(attendance.getNotes())
                .build();
    }

    /**
     * Update an existing TheoryLessonAttendance entity
     * with the values from the request.
     *
     * @param request The attendance request.
     * @param attendance The existing attendance entity.
     */
    public void updateEntity(
            TheoryLessonAttendanceRequest request,
            TheoryLessonAttendance attendance
    ) {
        TheoryLesson lesson = theoryLessonService.findEntityById(
                request.theoryLessonId()
        );

        StudentLicenseEnrollment enrollment =
                studentEnrollmentService.findEntityById(
                        request.studentEnrollmentId()
                );

        attendance.setTheoryLesson(lesson);
        attendance.setStudentLicenseEnrollment(enrollment);
        attendance.setStatus(request.status());
        attendance.setNotes(request.notes());
    }
}
