package com.zaljumaei.driveflow.student.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.BasePersonEntity;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.instructor.domain.Instructor;

@Entity
@Getter
@Setter
public class Student extends BasePersonEntity {

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
