package com.zaljumaei.driveflow.lesson.repository;

import com.zaljumaei.driveflow.lesson.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
