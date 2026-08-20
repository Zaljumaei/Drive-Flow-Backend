package com.zaljumaei.driveflow.instructor.domain;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.licenseclass.LicenseClass;
import com.zaljumaei.driveflow.student.domain.Student;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Instructor extends TenantScopedEntity {


    @Embedded
    //@TargetEmbeddable(PersonDetails.class)
    private PersonDetails personDetails;

    /**
     * Driving license that instructor can teach them
     */
    @ManyToMany
    @JoinTable(
            name = "instructor_license_classes",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "license_id")
    )
    private Set<LicenseClass> licenseClasses = new HashSet<>();

    @OneToMany
    private Set<Student> students = new HashSet<>();

    //utility methods to add and remove student and licenseclasses
    public boolean addStudent(Student student) {
        return this.students.add(student);
    }

    public boolean removeStudent(Student student) {
        return this.students.remove(student);
    }

    public boolean addLicenseClass(LicenseClass licenseClass) {
        return this.licenseClasses.add(licenseClass);
    }

    public boolean removeLicenseClass(LicenseClass licenseClass) {
        return this.licenseClasses.remove(licenseClass);
    }

}
