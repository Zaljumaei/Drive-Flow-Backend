package com.zaljumaei.driveflow.vehicle.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;

@Getter
@Setter
@Entity
public class Vehicle extends TenantScopedEntity {

    private String brand;

    private String model;

    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private DrivingSchool drivingSchool;
}
