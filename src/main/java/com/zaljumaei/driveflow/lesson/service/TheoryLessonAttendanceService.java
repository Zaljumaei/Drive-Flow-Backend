package com.zaljumaei.driveflow.lesson.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.domain.TheoryLessonAttendance;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceResponse;

public interface TheoryLessonAttendanceService {
    TheoryLessonAttendanceResponse create(
            TheoryLessonAttendanceRequest request
    );

    TheoryLessonAttendanceResponse findById(String id);

    PageResponse<TheoryLessonAttendanceResponse> findAll(
            int pageNumber
    );

    PageResponse<TheoryLessonAttendanceResponse> findByTheoryLessonId(
            String theoryLessonId,
            int pageNumber
    );

    TheoryLessonAttendanceResponse update(
            String id,
            TheoryLessonAttendanceRequest request
    );

    void delete(String id);

    TheoryLessonAttendance findEntityById(String id);

}
