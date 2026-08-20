package com.zaljumaei.driveflow.licenseclass;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import com.zaljumaei.driveflow.common.TenantScopedEntity;

@Entity
@Getter
@Setter
public class LicenseClass extends TenantScopedEntity {

    @Enumerated(EnumType.STRING)
    private String code;

    private String name;

    private String description;

}
