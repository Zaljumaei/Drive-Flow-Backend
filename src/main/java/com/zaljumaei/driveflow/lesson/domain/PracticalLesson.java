package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.vehicle.domain.Vehicle;

@Entity
public class PracticalLesson extends Lesson {

    @OneToOne
    private Student student;

    @OneToOne
    private Vehicle vehicle;

    private PracticalLessonStatus status;
}
