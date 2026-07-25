package com.zaljumaei.driveflow.drivingschool.service;

import com.zaljumaei.driveflow.common.BaseService;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;

import java.util.List;

public interface DrivingSchoolService extends BaseService<DrivingSchoolRequest, DrivingSchoolResponse> {

    DrivingSchoolResponse findByName(String drivingSchoolName);

    List<DrivingSchoolResponse> findAllDrivingSchools();

}
