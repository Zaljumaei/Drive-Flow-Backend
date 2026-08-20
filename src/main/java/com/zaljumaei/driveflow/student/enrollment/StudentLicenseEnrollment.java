package com.zaljumaei.driveflow.student.enrollment;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.licenseclass.LicenseClass;
import com.zaljumaei.driveflow.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 *Association entity class to manage the Student and LicenseClass
 */

@Entity
@Getter
@Setter
@Builder
public class StudentLicenseEnrollment extends TenantScopedEntity {

    /**
     * A student can register for many DriverLicense classes in the same time.
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "license_class_id")
    private LicenseClass licenseClass;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentEnrollmentLicenseStatus studentLicenseStatus;

    /**
     * Registration date of enrollment, that can be helpful for marketing purpose.
     */
    private LocalDate registrationDate = LocalDate.now();

    /**
     * Start date of the enrollment. Some DrivingSchool may have rules for Enrollment,
     * like after one year the enrollment should be renewed.
     * Status should be changed to ACTIVE
     */
    private LocalDate startDate;

    /**
     * Date of completion the enrolled DriverLicence class.
     * When the student compete the DriverLicence class successfully.
     * Status should be changed to COMPLETED.
     */
    private LocalDate completedDate;

    /**
     * When the student cancel his registration for the DriverLicence class.
     * Status should be changed to CANCELLED.
     */
    private LocalDate cancelledDate;

    private String notes;

    public StudentLicenseEnrollment() {

    }
}
