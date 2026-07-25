package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for drivingSchool that can be used by the admin of drivingSchool or the systemAdmin
 */
@RestController
@RequestMapping(path = "/api/driving-schools")
public class DrivingSchoolController {

    private final DrivingSchoolService drivingSchoolService;

    public DrivingSchoolController(DrivingSchoolService drivingSchoolService) {
        this.drivingSchoolService = drivingSchoolService;
    }

    /**
     * Update normal information for the DrivingSchool, that defined in DrivingSchoolRequest
     * @param id of drivingSchool.
     * @param drivingSchoolRequest the new date to be updated
     * @return the response after updated
     */
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<DrivingSchoolResponse> update(@PathVariable Long id, @RequestBody DrivingSchoolRequest drivingSchoolRequest) {
        DrivingSchoolResponse response = drivingSchoolService.update(id,drivingSchoolRequest);
        return ResponseEntity.ok(response);
    }
}
