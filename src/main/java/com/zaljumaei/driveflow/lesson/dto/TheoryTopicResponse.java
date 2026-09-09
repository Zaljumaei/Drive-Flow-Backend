package com.zaljumaei.driveflow.lesson.dto;

import lombok.Builder;

@Builder
public record TheoryTopicResponse (
        String id,
        String title,
        String description,
        int topicNumber
) { }
