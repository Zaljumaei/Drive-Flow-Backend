package com.zaljumaei.driveflow.lesson.service;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;

public interface TheoryTopicService {

    TheoryTopic addTopic(TheoryTopicRequest request);

    void deleteTopic(String theoryTopicId);

    TheoryTopic updateTopic(TheoryTopicRequest request, String theoryTopicId);
}
