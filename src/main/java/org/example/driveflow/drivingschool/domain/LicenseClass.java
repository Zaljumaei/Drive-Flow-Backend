package org.example.driveflow.drivingschool.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import org.example.driveflow.common.AbstractEntity;
import org.example.driveflow.student.domain.Student;

//TODO should this class be enum to include known LicenseClasses in Germany like A B..... or we let every school add them manually
@Entity
@Getter
@Setter
public class LicenseClass extends AbstractEntity {

    private String description;

    @OneToOne(mappedBy = "licenseClass")
    private Student student;


    //TODO think about adding DrivingSchool to be bidirectional Mapping
}
