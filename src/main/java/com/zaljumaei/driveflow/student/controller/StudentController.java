package com.zaljumaei.driveflow.student.controller;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.student.dtos.CreateStudentRequest;
import com.zaljumaei.driveflow.student.dtos.StudentResponse;
import com.zaljumaei.driveflow.student.dtos.UpdateStudentRequest;
import com.zaljumaei.driveflow.student.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class to handel HTTP requests for the student.
 * And delegate the actual operations to the service layer.
 */
@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Process StudentRequest to the service to create new entity of student.
     * @param request The request with the new data.
     * @return StudentResponse with status CREATED if the student is created successfully.
     */
    @PostMapping("/create")
    public ResponseEntity<StudentResponse> processStudentRequest(@RequestBody CreateStudentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.create(request));
    }

    /**
     * Handle the HTTP request to get existed entity.
     * @param id The id of student
     * @return StudentResponse.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable String id) {
        return ResponseEntity.ok().body(studentService.findById(id));
    }

    /**
     * Handle the HTTP request to get page of students of the drivingSchool.
     * Retrieves a specific page of students, which is applied in the service layer.
     * @param page The number of page, it starts by zero.
     * @return The page of Student.
     */
    @GetMapping("/all")
    public ResponseEntity<PageResponse<StudentResponse>> getAllStudents(@RequestParam int page) {
        return ResponseEntity.ok().body(studentService.findAll(page));
    }
    /**
     * Handle the HTTP request to update existed entity.
     * @param id The id of student
     * @param request The Dto containing the new data
     * @return StudentResponse after update.
     */
    @PostMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudentById(@PathVariable String id, @RequestBody UpdateStudentRequest request) {
        return ResponseEntity.ok().body(studentService.update(id, request));
    }

    /**
     * Handle HTTP request to delete existed student entity.
     * @param id The id of the student to be deleted.
     * @return ResponseEntity with HTTP status of NO_CONTENT
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable String id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
