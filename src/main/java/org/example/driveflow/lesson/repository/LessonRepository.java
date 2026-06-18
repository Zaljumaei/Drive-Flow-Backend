package org.example.driveflow.lesson.repository;

import org.example.driveflow.lesson.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
