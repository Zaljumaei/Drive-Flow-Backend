package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.Entity;
import lombok.*;

import com.zaljumaei.driveflow.common.TenantScopedEntity;

/**
 * Entity class for theory topics.
 * The topic in all DrivingSchool are almost the same,
 * this class let each driving school set own naming and order.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheoryTopic extends TenantScopedEntity {

    private String title;

    private String description;

    private int topicNumber;
}