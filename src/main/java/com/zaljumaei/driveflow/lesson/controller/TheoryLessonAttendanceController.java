package com.zaljumaei.driveflow.lesson.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceResponse;
import com.zaljumaei.driveflow.lesson.service.TheoryLessonAttendanceService;

@RestController
@RequestMapping("/api/theory-lesson-attendances")
public class TheoryLessonAttendanceController {

    private final TheoryLessonAttendanceService attendanceService;

    public TheoryLessonAttendanceController(TheoryLessonAttendanceService theoryLessonAttendanceService) {
        this.attendanceService  = theoryLessonAttendanceService;
    }

    /**
     * Create a new theory lesson attendance.
     *
     * @param request The attendance request.
     * @return The created attendance response.
     */
    @PostMapping
    public ResponseEntity<TheoryLessonAttendanceResponse> create(
            @Valid @RequestBody TheoryLessonAttendanceRequest request
    ) {
        TheoryLessonAttendanceResponse response = attendanceService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Find a theory lesson attendance by id.
     *
     * @param id The attendance id.
     * @return The attendance response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TheoryLessonAttendanceResponse> findById(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(attendanceService.findById(id));
    }

    /**
     * Find all theory lesson attendances with pagination.
     *
     * @param pageNumber The page number, starting at 0.
     * @return A paginated attendance response.
     */
    @GetMapping
    public ResponseEntity<PageResponse<TheoryLessonAttendanceResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber
    ) {
        return ResponseEntity.ok(attendanceService.findAll(pageNumber));
    }

    /**
     * Find all attendances for a specific theory lesson.
     *
     * @param theoryLessonId The theory lesson id.
     * @param pageNumber The page number, starting at 0.
     * @return A paginated attendance response.
     */
    @GetMapping("/theory-lessons/{theoryLessonId}")
    public ResponseEntity<PageResponse<TheoryLessonAttendanceResponse>>
    findByTheoryLessonId(@PathVariable String theoryLessonId,
            @RequestParam(defaultValue = "0") int pageNumber
    ) {
        return ResponseEntity.ok(
                attendanceService.findByTheoryLessonId(theoryLessonId, pageNumber));
    }

    /**
     * Update an existing theory lesson attendance.
     *
     * @param id The attendance id.
     * @param request The updated attendance data.
     * @return The updated attendance response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TheoryLessonAttendanceResponse> update(@PathVariable String id,
            @RequestBody TheoryLessonAttendanceRequest request
    ) {
        return ResponseEntity.ok(attendanceService.update(id, request));
    }

    /**
     * Delete a theory lesson attendance by id.
     *
     * @param id The attendance id.
     * @return HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        attendanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
