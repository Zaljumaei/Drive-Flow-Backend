package com.zaljumaei.driveflow.lesson.service.impl;

import org.springframework.stereotype.Service;

import com.zaljumaei.driveflow.lesson.domain.TheoryTopic;
import com.zaljumaei.driveflow.lesson.dto.TheoryTopicRequest;
import com.zaljumaei.driveflow.lesson.repository.TheoryTopicRepository;
import com.zaljumaei.driveflow.lesson.service.TheoryTopicService;

@Service
public class TheoryTopicServiceImpl implements TheoryTopicService {

    private final TheoryTopicRepository theoryTopicRepository;

    public TheoryTopicServiceImpl(TheoryTopicRepository topicRepository) {
        this.theoryTopicRepository = topicRepository;
    }

    @Override
    public TheoryTopic addTopic(TheoryTopicRequest request) {
        return null;
    }

    @Override
    public void deleteTopic(TheoryTopicRequest request) {

    }

    @Override
    public TheoryTopic updateTopic(TheoryTopicRequest request) {
        return null;
    }
}
