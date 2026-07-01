package org.example.driveflow.drivingschool.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.driveflow.common.AbstractEntity;
import org.example.driveflow.common.Address;
import org.example.driveflow.instructor.domain.Instructor;
import org.example.driveflow.student.domain.Student;
import org.example.driveflow.vehicle.domain.Vehicle;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class DrivingSchool extends AbstractEntity {

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

    public void addInstructor(Instructor instructor) {
        instructor.setDrivingSchool(this);
        //this.instructors.add(instructor);
        this.getInstructors().add(instructor);
    }

    public void addStudent(Student student) {
        student.setDrivingSchool(this);
        this.getStudents().add(student);
    }

    public void addVehicle(Vehicle vehicle) {
        vehicle.setDrivingSchool(this);
        this.getVehicles().add(vehicle);
    }

    public void addLicenseClass(LicenseClass licenseClass) {
        this.getLicenseClasses().add(licenseClass);
    }



}
