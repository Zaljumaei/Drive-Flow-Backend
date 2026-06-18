package org.example.driveflow.lesson.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import org.example.driveflow.student.domain.Student;
import org.example.driveflow.vehicle.domain.Vehicle;

@Entity
public class PracticalLesson extends Lesson {

    @OneToOne
    private Student student;

    @OneToOne
    private Vehicle vehicle;

    private PracticalLessonStatus status;
}
