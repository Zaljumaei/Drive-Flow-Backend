package org.example.driveflow.lesson.domain;

import jakarta.persistence.*;
import org.example.driveflow.common.AbstractEntity;
import org.example.driveflow.instructor.domain.Instructor;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Lesson extends AbstractEntity {

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;

}
