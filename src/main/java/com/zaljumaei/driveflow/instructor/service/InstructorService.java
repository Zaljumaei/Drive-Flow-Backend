package com.zaljumaei.driveflow.instructor.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.instructor.domain.Instructor;
import com.zaljumaei.driveflow.instructor.dtos.*;


public interface InstructorService {

    InstructorResponse create(CreateInstructorRequest request);

    InstructorResponse update(String id, UpdateInstructorRequest request);

    InstructorResponse findById(String id);

    PageResponse<InstructorResponse> findAll(int page);

    void delete(String id);
}
