package com.zaljumaei.driveflow.drivingschool.domain;

import com.zaljumaei.driveflow.instructor.domain.Instructor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.AbstractEntity;
import com.zaljumaei.driveflow.student.domain.Student;

import java.util.Set;

//TODO should this class be enum to include known LicenseClasses in Germany like A B..... or we let every school add them manually
@Entity
@Getter
@Setter
public class LicenseClass extends AbstractEntity {

    private String description;

    @OneToOne(mappedBy = "licenseClass")
    private Student student;

    @ManyToMany(mappedBy = "licenseClasses")
    private Set<Instructor> instructors;
    //TODO think about adding DrivingSchool to be bidirectional Mapping
}
