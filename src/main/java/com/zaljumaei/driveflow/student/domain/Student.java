package com.zaljumaei.driveflow.student.domain;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.licenseclass.StudentLicenseEnrollment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.licenseclass.LicenseClass;
import com.zaljumaei.driveflow.instructor.domain.Instructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Student extends TenantScopedEntity {

    @Embedded
    private PersonDetails personDetails;

    //Student can register for multiple driving license
    /*@ManyToMany
    @JoinTable(
            name = "student_license_classes",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "license_class_id")
    )
    private Set<LicenseClass> licenseClass = new HashSet<>();
    */

    @OneToMany(mappedBy = "student")
    private Set<StudentLicenseEnrollment> studentLicenseClasses = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public void addLicenseClass(StudentLicenseEnrollment studentLicenseClass) {
        this.studentLicenseClasses.add(studentLicenseClass);
    }

    public void removeLicenseClass(StudentLicenseEnrollment studentLicenseClass) {
        this.studentLicenseClasses.remove(studentLicenseClass);
    }

}
