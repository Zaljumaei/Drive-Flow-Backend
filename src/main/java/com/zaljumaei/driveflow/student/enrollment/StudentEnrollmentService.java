package com.zaljumaei.driveflow.student.enrollment;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.student.enrollment.dto.EnrollmentDateRequest;
import com.zaljumaei.driveflow.student.enrollment.dto.StudentEnrollmentRequest;
import com.zaljumaei.driveflow.student.enrollment.dto.StudentEnrollmentResponse;
import com.zaljumaei.driveflow.student.enrollment.dto.UpdateStudentEnrollmentRequest;

import java.util.Set;


public interface StudentEnrollmentService {

    StudentEnrollmentResponse enrollStudent(StudentEnrollmentRequest enrollmentRequest);

    Set<StudentEnrollmentResponse> findEnrollmentByStudentId(String id);

    PageResponse<StudentEnrollmentResponse> findAll(int page);

    StudentEnrollmentResponse setStartDate(EnrollmentDateRequest request);

    StudentEnrollmentResponse setCompletedDate(EnrollmentDateRequest request);

    StudentEnrollmentResponse setCancelledDate(EnrollmentDateRequest request);

    StudentEnrollmentResponse updateEnrollment(String enrollmentId, UpdateStudentEnrollmentRequest request);

    void deleteEnrollment(String enrollmentId);

    StudentEnrollmentResponse findById(String enrollmentId);

    StudentLicenseEnrollment findEntityById(String enrollmentId);
}
