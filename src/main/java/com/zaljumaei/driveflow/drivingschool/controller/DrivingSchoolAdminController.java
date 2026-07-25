package com.zaljumaei.driveflow.drivingschool.controller;

import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller that can bes used from the systemAdmin to (e.g) create and delete DrivingSchool (Tenant).
 * DrivingSchool admins should use the {@link DrivingSchoolController}.
 */

@RestController
@RequestMapping(path = "/api/admin/driving-schools", produces = MediaType.APPLICATION_JSON_VALUE)
public class DrivingSchoolAdminController {

    private final DrivingSchoolService drivingSchoolService;

    public DrivingSchoolAdminController(DrivingSchoolService drivingSchoolService) {
        this.drivingSchoolService = drivingSchoolService;
    }

    @PostMapping("/create")
    public ResponseEntity<DrivingSchoolResponse> processDrivingSchool(@RequestBody DrivingSchoolRequest drivingSchoolRequest) {
        DrivingSchoolResponse response = drivingSchoolService.create(drivingSchoolRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrivingSchoolResponse> getDrivingSchoolById(@PathVariable Long id) {
        DrivingSchoolResponse response = drivingSchoolService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<DrivingSchoolResponse> getDrivingSchoolByName(@RequestParam(value = "name") String name) {
        DrivingSchoolResponse response = drivingSchoolService.findByName(name);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/allDrSchools")
    public ResponseEntity<List<DrivingSchoolResponse>> getAllDrivingSchools() {
        List<DrivingSchoolResponse> listResponse = drivingSchoolService.findAllDrivingSchools();
        return ResponseEntity.ok().body(listResponse);
    }


    //Todo
    //create method to return number of students, instructors, vehicles., MayBe create Dto, that contain thus infos plus normal info.

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDrivingSchool(@PathVariable Long id) {
        drivingSchoolService.delete(id);
    }


}
