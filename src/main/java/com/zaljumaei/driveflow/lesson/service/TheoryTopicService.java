package com.zaljumaei.driveflow.lesson.service;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;

public interface TheoryTopicService {

    TheoryTopic addTopic(TheoryTopicRequest request);

    void deleteTopic(TheoryTopicRequest request);

    TheoryTopic updateTopic(TheoryTopicRequest request);
}
