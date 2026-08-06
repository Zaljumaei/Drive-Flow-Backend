package com.zaljumaei.driveflow.instructor.domain;

import com.zaljumaei.driveflow.common.TenantScopedEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.drivingschool.domain.LicenseClass;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.student.domain.Student;
import org.hibernate.annotations.TargetEmbeddable;
import org.hibernate.boot.internal.Target;

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
            name = "license_classes",
            joinColumns = @JoinColumn(name = "instructor_id"),
            inverseJoinColumns = @JoinColumn(name = "license_id")
    )
    private Set<LicenseClass> licenseClasses = new HashSet<>();

    @OneToMany
    private Set<Student> students = new HashSet<>();


}
