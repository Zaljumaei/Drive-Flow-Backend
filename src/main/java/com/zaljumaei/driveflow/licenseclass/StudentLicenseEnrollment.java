package com.zaljumaei.driveflow.licenseclass;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.student.domain.Student;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StudentLicenseEnrollment extends TenantScopedEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "license_class_id")
    private LicenseClass licenseClass;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudentLicenseStatus studentLicenseStatus;

    @Column(nullable = false)
    private LocalDate registrationDate;

    private LocalDate startDate;

    private LocalDate completedDate;

    private LocalDate cancelledDate;

    private String notes;

}
