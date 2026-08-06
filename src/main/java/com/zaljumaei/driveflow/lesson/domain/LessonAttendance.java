package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.*;
import com.zaljumaei.driveflow.common.TenantScopedEntity;
import com.zaljumaei.driveflow.student.domain.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the status for TheoryLesson
 */
@Entity
public class LessonAttendance extends TenantScopedEntity {

    @OneToOne
    private Student student;

    @OneToMany
    private Set<TheoryLesson>  theories = new HashSet<>();

    private TheoryLessonStatus status;

}
