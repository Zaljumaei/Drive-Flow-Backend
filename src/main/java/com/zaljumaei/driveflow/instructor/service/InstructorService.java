package com.zaljumaei.driveflow.instructor.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.instructor.domain.Instructor;
import com.zaljumaei.driveflow.instructor.dtos.*;


public interface InstructorService {

    InstructorResponse create(CreateInstructorRequest request);

    InstructorResponse update(Long id, UpdateInstructorRequest request);

    InstructorResponse findById(Long id);

    PageResponse<Instructor> findAll(int page);

    void delete(Long id);
}
