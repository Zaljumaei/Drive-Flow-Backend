package org.example.driveflow.instructor.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.example.driveflow.common.BasePersonEntity;
import org.example.driveflow.drivingschool.domain.LicenseClass;
import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.example.driveflow.student.domain.Student;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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
