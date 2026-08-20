package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.*;
import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.instructor.domain.Instructor;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Lesson extends TenantScopedEntity {

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;

}
