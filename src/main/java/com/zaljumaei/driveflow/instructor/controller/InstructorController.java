package com.zaljumaei.driveflow.instructor.controller;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.instructor.dtos.CreateInstructorRequest;
import com.zaljumaei.driveflow.instructor.dtos.InstructorRequest;
import com.zaljumaei.driveflow.instructor.dtos.InstructorResponse;
import com.zaljumaei.driveflow.instructor.service.InstructorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @PostMapping("/create")
    public ResponseEntity<InstructorResponse> processInstructorRequest(@RequestBody CreateInstructorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.create(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<InstructorResponse>> getAllInstructors(int pageNumber) {
        return ResponseEntity.ok().body(instructorService.findAll(pageNumber));
    }
}
