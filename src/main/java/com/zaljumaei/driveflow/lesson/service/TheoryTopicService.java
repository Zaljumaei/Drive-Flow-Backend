package com.zaljumaei.driveflow.lesson.service;

import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicResponse;

import java.util.List;

public interface TheoryTopicService {

    TheoryTopicResponse addTopic(TheoryTopicRequest request);

    void deleteTopic(String theoryTopicId);

    TheoryTopicResponse updateTopic(TheoryTopicRequest request, String theoryTopicId);

    List<TheoryTopicResponse> getAll();
}
