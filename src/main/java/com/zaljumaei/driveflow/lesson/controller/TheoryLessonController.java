package com.zaljumaei.driveflow.lesson.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLesson.TheoryLessonResponse;
import com.zaljumaei.driveflow.lesson.service.TheoryLessonService;

@RestController
@RequestMapping("/api/theory-lessons")
@RequiredArgsConstructor
public class TheoryLessonController {

    private final TheoryLessonService theoryLessonService;

    /**
     * Create a new theory lesson.
     *
     * @param request The theory lesson request.
     * @return The created theory lesson.
     */
    @PostMapping
    public ResponseEntity<TheoryLessonResponse> create(
            @Valid @RequestBody TheoryLessonRequest request
    ) {
        TheoryLessonResponse response =
                theoryLessonService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * Find a theory lesson by id.
     *
     * @param id The theory lesson id.
     * @return The theory lesson response.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TheoryLessonResponse> findById( @PathVariable String id) {
        TheoryLessonResponse response = theoryLessonService.findById(id);

        return ResponseEntity.ok(response);
    }

    /**
     * Find all theory lessons with pagination.
     *
     * @param pageNumber The page number (starts at 0).
     * @return A paginated response of theory lessons.
     */
    @GetMapping
    public ResponseEntity<PageResponse<TheoryLessonResponse>> findAll(
            @RequestParam(defaultValue = "0") int pageNumber
    ) {
        return ResponseEntity.ok(theoryLessonService.findAll(pageNumber));
    }

    /**
     * Update an existing theory lesson.
     *
     * @param id The theory lesson id.
     * @param request The updated theory lesson data.
     * @return The updated theory lesson response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TheoryLessonResponse> update( @PathVariable String id,
            @RequestBody TheoryLessonRequest request
    ) {
        TheoryLessonResponse response = theoryLessonService.update(id, request);

        return ResponseEntity.ok(response);
    }

    /**
     * Delete a theory lesson by id.
     *
     * @param id The theory lesson id.
     * @return HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete( @PathVariable String id) {
        theoryLessonService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

