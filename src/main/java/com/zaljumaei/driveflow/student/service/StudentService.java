package com.zaljumaei.driveflow.student.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.student.dtos.CreateStudentRequest;
import com.zaljumaei.driveflow.student.dtos.StudentResponse;
import com.zaljumaei.driveflow.student.dtos.UpdateStudentRequest;

/**
 * StudentService, Hibernate will handel the tenant,
 * so we should not set the tenantId with every operation to the repository.
 */

public interface StudentService {

    StudentResponse create(CreateStudentRequest request);

    StudentResponse findById(String id);

    PageResponse<StudentResponse> findAll(int page);

    StudentResponse update(String id, UpdateStudentRequest request);

    void delete(String id);

}
