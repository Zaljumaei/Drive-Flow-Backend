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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Instructor>  instructors =  new HashSet<Instructor>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Student> students =  new HashSet<Student>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "drivingSchool")
    private Set<Vehicle> vehicles =  new HashSet<Vehicle>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<LicenseClass> licenseClasses = new HashSet<LicenseClass>();

    public DrivingSchool() {

    }

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
