package com.zaljumaei.driveflow.instructor.controller;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.instructor.dtos.CreateInstructorRequest;
import com.zaljumaei.driveflow.instructor.dtos.InstructorRequest;
import com.zaljumaei.driveflow.instructor.dtos.InstructorResponse;
import com.zaljumaei.driveflow.instructor.dtos.UpdateInstructorRequest;
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

    /**
     * Process new InstructorRequest to create new instructor entity
     * @param request The request with data of the instructor entity
     * @return ResponseEntity with the CREATED status if the instructor successfully is created.
     */
    @PostMapping("/create")
    public ResponseEntity<InstructorResponse> processInstructorRequest(@RequestBody CreateInstructorRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instructorService.create(request));
    }

    @GetMapping
    public ResponseEntity<PageResponse<InstructorResponse>> getAllInstructors(int pageNumber) {
        return ResponseEntity.ok().body(instructorService.findAll(pageNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponse> getInstructorById(@PathVariable String id) {
        return ResponseEntity.ok().body(instructorService.findById(id));
    }

    /**
     * partially update for instructor
     * @param id The id of instructor to be updated
     * @param request The request with the new data
     * @return ResponseEntity with Instructor after updated.
     */
    @PatchMapping("update/{id}")
    public ResponseEntity<InstructorResponse> updateInstructorById(@PathVariable String id, @RequestBody UpdateInstructorRequest request) {
        return ResponseEntity.ok().body(instructorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInstructorById(@PathVariable String id) {
        instructorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
