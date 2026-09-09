package com.zaljumaei.driveflow.lesson.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent the TheoryLesson which can take place many times for the same topic.
 */
@Entity
@Getter
@Setter
public class TheoryLesson extends Lesson {

    @OneToMany(mappedBy = "theoryLesson")
    private Set<TheoryLessonAttendance> theoryLessonAttendance = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TheoryTopic topic;
}
