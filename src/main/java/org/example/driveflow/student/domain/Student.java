package org.example.driveflow.student.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.driveflow.common.BasePersonEntity;
import org.example.driveflow.drivingschool.domain.LicenseClass;
import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.example.driveflow.instructor.domain.Instructor;

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
