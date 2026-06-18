package org.example.driveflow.lesson.domain;

import jakarta.persistence.*;
import org.example.driveflow.instructor.domain.Instructor;

import java.time.LocalDate;

@MappedSuperclass
public abstract class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Instructor instructor;

}
