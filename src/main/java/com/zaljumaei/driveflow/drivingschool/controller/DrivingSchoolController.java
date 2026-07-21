package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for drivingSchool that can be used by the admin of drivingSchool or the systemAdmin
 */
@RestController
@RequestMapping(path = "/drivingschool")
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
    public DrivingSchoolResponse update(@PathVariable Long id, @RequestBody DrivingSchoolRequest drivingSchoolRequest) {
        return drivingSchoolService.update(id,drivingSchoolRequest);
    }
}
