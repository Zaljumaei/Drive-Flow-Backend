package org.example.driveflow.drivingschool.domain;

import jakarta.persistence.*;
import org.example.driveflow.common.Address;
import org.example.driveflow.drivingschool.LicenseClass;
import org.example.driveflow.instructor.domain.Instructor;
import org.example.driveflow.student.Student;
import org.example.driveflow.vehicle.Vehicle;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DrivingSchool {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Embedded
    private Address address;

    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Instructor>  instructors =  new HashSet<Instructor>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Student> students =  new HashSet<Student>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Vehicle> vehicles =  new HashSet<Vehicle>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<LicenseClass> licenseClasses = new HashSet<LicenseClass>();

    //private Set<Lesson>



}
