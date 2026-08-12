package com.zaljumaei.driveflow.student.domain;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "license_id")
    private Set<LicenseClass> licenseClass = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public void addLicenseClass(LicenseClass licenseClass) {
        licenseClass.setStudent(this);
        this.licenseClass.add(licenseClass);
    }

}
