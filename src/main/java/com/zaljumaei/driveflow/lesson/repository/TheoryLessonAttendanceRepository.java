package com.zaljumaei.driveflow.lesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zaljumaei.driveflow.lesson.domain.TheoryLessonAttendance;

public interface TheoryLessonAttendanceRepository extends JpaRepository<TheoryLessonAttendance, String> {

    Page<TheoryLessonAttendance> findByTheoryLesson_Id(
            String theoryLessonId,
            Pageable pageable
    );

    boolean existsByTheoryLesson_IdAndStudentLicenseEnrollment_Student_Id(
            String theoryLessonId,
            String studentId
    );

    boolean existsByTheoryLesson_IdAndStudentLicenseEnrollment_Student_IdAndIdNot(
            String theoryLessonId,
            String studentId,
            String attendanceId
    );
}
