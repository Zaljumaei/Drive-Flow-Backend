package com.zaljumaei.driveflow.lesson.repository;

import com.zaljumaei.driveflow.lesson.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheoryLessonRepository extends JpaRepository<Lesson, String> {
}
