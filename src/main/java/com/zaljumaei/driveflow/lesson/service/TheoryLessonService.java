package com.zaljumaei.driveflow.lesson.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.domain.TheoryLesson;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonResponse;


/**
 * Service for TheoryLesson.
 * The request and response pattern (dto) is used in most methode.
 */
public interface TheoryLessonService {

    TheoryLessonResponse create(TheoryLessonRequest request);

    TheoryLessonResponse findById(String id);

    PageResponse<TheoryLessonResponse> findAll(int pageNumber);

    TheoryLessonResponse update(
            String id,
            TheoryLessonRequest request
    );

    void delete(String id);

    TheoryLesson findEntityById(String id);
}
