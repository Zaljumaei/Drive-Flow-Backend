package com.zaljumaei.driveflow.licenseclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import com.zaljumaei.driveflow.common.TenantScopedEntity;

@Entity
@Getter
@Setter
public class LicenseClass extends TenantScopedEntity {

    private String name;

    private String description;

}
