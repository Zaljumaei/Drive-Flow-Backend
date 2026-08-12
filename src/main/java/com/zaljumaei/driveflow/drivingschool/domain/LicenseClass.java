package com.zaljumaei.driveflow.drivingschool.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import com.zaljumaei.driveflow.instructor.domain.Instructor;
import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.student.domain.Student;

import java.util.Set;

//TODO should this class be enum to include known LicenseClasses in Germany like A B..... or we let every school add them manually
@Entity
@Getter
@Setter
public class LicenseClass extends TenantScopedEntity {

    private String description;

    @ManyToOne
    private Student student;

    @ManyToMany(mappedBy = "licenseClasses")
    private Set<Instructor> instructors;
}
