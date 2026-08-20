package com.zaljumaei.driveflow.student.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnrollmentRepository extends JpaRepository<StudentLicenseEnrollment, String> {
}
