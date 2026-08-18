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

@Entity
@Getter
@Setter
public class LicenseClass extends TenantScopedEntity {

    private String name;

    private String description;

}
