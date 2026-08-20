package com.zaljumaei.driveflow.drivingschool.domain;

import com.zaljumaei.driveflow.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.instructor.domain.Instructor;
import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.vehicle.domain.Vehicle;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class DrivingSchool extends BaseEntity {

    private String name;

    @Email
    private String email;

    @Embedded
    private Address address;

    private String phoneNumber;


    public DrivingSchool() {

    }

}
