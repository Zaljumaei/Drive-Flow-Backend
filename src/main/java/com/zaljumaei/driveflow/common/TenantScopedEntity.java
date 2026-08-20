package com.zaljumaei.driveflow.common;


import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.TenantId;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class TenantScopedEntity extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, insertable=false, updatable=false)
    private DrivingSchool drivingSchool;

    @TenantId
    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
}
