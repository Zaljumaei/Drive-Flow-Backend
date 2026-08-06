package com.zaljumaei.driveflow.student.domain;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.instructor.domain.Instructor;

@Entity
@Getter
@Setter
public class Student extends TenantScopedEntity {

    @Embedded
    private PersonDetails personDetails;

    @OneToOne(cascade = CascadeType.ALL)
    private LicenseClass driverLicenseClass;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private DrivingSchool drivingSchool;

    @OneToOne
    @JoinColumn(name = "license_id")
    private LicenseClass licenseClass;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

}
