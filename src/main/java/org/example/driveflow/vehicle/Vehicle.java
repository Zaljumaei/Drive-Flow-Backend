package org.example.driveflow.vehicle;

import jakarta.persistence.*;
import org.example.driveflow.drivingschool.domain.DrivingSchool;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String brand;

    private String model;

    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private DrivingSchool drivingSchool;
}
