package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that can bes used from the systemAdmin to (e.g) create and delete DrivingSchool (Tenant).
 * DrivingSchool admins should use the {@link DrivingSchoolController}.
 */

@RestController
@RequestMapping(path = "/drivingschoolsforadmin", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrivingSchoolAdminController {

    private final DrivingSchoolService drivingSchoolService;

    public DrivingSchoolAdminController(DrivingSchoolService drivingSchoolService) {
        this.drivingSchoolService = drivingSchoolService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public DrivingSchoolResponse processDrivingSchool(@RequestBody DrivingSchoolRequest drivingSchoolRequest) {
        return drivingSchoolService.create(drivingSchoolRequest);
    }

    @GetMapping("/{id}")
    public DrivingSchoolResponse getDrivingSchoolById(@PathVariable Long id) {
        return drivingSchoolService.findById(id);
    }

    @GetMapping("/{name}")
    public DrivingSchoolResponse getDrivingSchoolByName(@PathVariable String name) {
        return drivingSchoolService.findByName(name);
    }

    @GetMapping(value = "/allDrSchools")
    public List<DrivingSchoolResponse> getAllDrivingSchools() {
        return drivingSchoolService.findAllDrivingSchools();
    }


    //Todo
    //create method to return number of students, instructors, vehicles., MayBe create Dto, that contain thus infos plus normal info.

    @DeleteMapping(value = "/delete/{id}")
    public void deleteDrivingSchool(@PathVariable Long id) {
        drivingSchoolService.delete(id);
    }


}
