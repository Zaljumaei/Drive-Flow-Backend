package org.example.driveflow.lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import org.example.driveflow.student.Student;
import org.example.driveflow.vehicle.Vehicle;

@Entity
public class PracticalLesson extends Lesson {

    @OneToOne
    private Student student;

    @OneToOne
    private Vehicle vehicle;

    private PracticalLessonStatus status;
}
