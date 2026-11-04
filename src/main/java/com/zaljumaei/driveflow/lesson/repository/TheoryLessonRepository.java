package com.zaljumaei.driveflow.lesson.repository;

import com.zaljumaei.driveflow.lesson.domain.TheoryLesson;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TheoryLessonRepository extends JpaRepository<TheoryLesson, String> {
}
