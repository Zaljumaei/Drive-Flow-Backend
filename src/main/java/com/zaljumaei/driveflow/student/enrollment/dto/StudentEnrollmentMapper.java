package com.zaljumaei.driveflow.student.enrollment.dto;

import com.zaljumaei.driveflow.licenseclass.LicenseClass;
import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.student.enrollment.StudentEnrollmentLicenseStatus;
import com.zaljumaei.driveflow.student.enrollment.StudentLicenseEnrollment;

import org.springframework.stereotype.Component;

/**
 * StudentEnrollment mapper to convert request to response or entity.
 */
@Component
public class StudentEnrollmentMapper {


    /**
     * Map request to {@link StudentLicenseEnrollment}.
     * If the startDate is given, so the enrollment is active, otherwise is planned.
     *
     * @param student The Student that register for DrivingLicenseClass.
     * @param licenseClass The DrivingLicenseClass, which the student register for.
     * @param studentEnrollmentRequest The request, that hold other data.
     * @return created {@link StudentLicenseEnrollment} entity.
     */
    public StudentLicenseEnrollment toEntity(Student student,
                                             LicenseClass licenseClass,
                                             StudentEnrollmentRequest studentEnrollmentRequest) {
        StudentLicenseEnrollment studentLicenseEnrollment = StudentLicenseEnrollment
                .builder()
                .student(student)
                .licenseClass(licenseClass)
                .notes(studentEnrollmentRequest.notes())
                .build();

        if (studentEnrollmentRequest.startDate() != null) {
            studentLicenseEnrollment.setStartDate(studentEnrollmentRequest.startDate());
            studentLicenseEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.ACTIVE);
        }else {
            studentLicenseEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.PLANNED);
        }

        return studentLicenseEnrollment;
    }

    /**
     * Map {@link StudentLicenseEnrollment} entity to response.
     *
     * @param studentLicenseEnrollment The entity
     * @return response
     */
    public StudentEnrollmentResponse toResponse(StudentLicenseEnrollment studentLicenseEnrollment) {

        return StudentEnrollmentResponse.builder()
                .enrollmentId(studentLicenseEnrollment.getId())
                .licenseClassCode(studentLicenseEnrollment.getLicenseClass().getCode())
                .registrationDate(studentLicenseEnrollment.getRegistrationDate())
                .startDate(studentLicenseEnrollment.getStartDate())
                .enrollmentStatus(studentLicenseEnrollment.getStudentLicenseStatus().toString())
                .build();
    }

}
