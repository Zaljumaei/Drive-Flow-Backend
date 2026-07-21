package com.zaljumaei.driveflow.instructor.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.BasePersonEntity;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.student.domain.Student;

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
