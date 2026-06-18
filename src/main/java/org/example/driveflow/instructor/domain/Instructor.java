package org.example.driveflow.instructor.domain;

import jakarta.persistence.*;

import org.example.driveflow.common.BasePersonEntity;
import org.example.driveflow.drivingschool.LicenseClass;
import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.example.driveflow.student.Student;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Instructor extends BasePersonEntity {


    @ManyToOne
    @JoinColumn(name = "school_id")
    private DrivingSchool drivingSchool;

    /**
     * Driving license that instructor can teach them
     */
    @ManyToMany
    @JoinColumn(name = "license_id")
    private Set<LicenseClass> licenseClasses = new HashSet<>();

    @OneToMany
    private Set<Student> students = new HashSet<>();


}
