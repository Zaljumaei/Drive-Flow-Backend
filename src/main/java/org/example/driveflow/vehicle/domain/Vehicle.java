package org.example.driveflow.vehicle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.driveflow.common.AbstractEntity;
import org.example.driveflow.drivingschool.domain.DrivingSchool;

@Getter
@Setter
@Entity
public class Vehicle extends AbstractEntity {

    private String brand;

    private String model;

    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private DrivingSchool drivingSchool;
}
