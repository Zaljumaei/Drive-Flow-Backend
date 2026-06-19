package org.example.driveflow.lesson.domain;

import jakarta.persistence.*;
import org.example.driveflow.common.AbstractEntity;
import org.example.driveflow.student.domain.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to manage the status for TheoryLesson
 */
@Entity
public class LessonAttendance extends AbstractEntity {

    @OneToOne
    private Student student;

    @OneToMany
    private Set<TheoryLesson>  theories = new HashSet<>();

    private TheoryLessonStatus status;

}
