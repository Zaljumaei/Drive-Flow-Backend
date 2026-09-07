package com.zaljumaei.driveflow.lesson.repository;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface to access the database for TheoryTopic, so drivingSchool can add the topics, that are get taught there.
 */
public interface TheoryTopicRepository extends JpaRepository<TheoryTopic, String> {
}
