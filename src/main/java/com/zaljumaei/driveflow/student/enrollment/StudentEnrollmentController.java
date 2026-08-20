package com.zaljumaei.driveflow.student.enrollment;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.student.enrollment.dto.EnrollmentDateRequest;
import com.zaljumaei.driveflow.student.enrollment.dto.StudentEnrollmentRequest;
import com.zaljumaei.driveflow.student.enrollment.dto.StudentEnrollmentResponse;
import com.zaljumaei.driveflow.student.enrollment.dto.UpdateStudentEnrollmentRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * RestController class to handel HTTP request for StudentEnrollments.
 * It delegates the actual operations to the service layer.
 */
@RestController
@RequestMapping("api/student-enrollment/")
public class StudentEnrollmentController {

    private final StudentEnrollmentService studentEnrollmentService;

    public StudentEnrollmentController(StudentEnrollmentService studentEnrollmentService) {
        this.studentEnrollmentService = studentEnrollmentService;
    }

    /**
     * Handle HTTP request to creat a {@link StudentLicenseEnrollment} entity
     * by processing the request to the service layer
     *
     * @param studentEnrollmentRequest The request with data to create student enrollment.
     * @return responseEntity containing the {@link StudentEnrollmentResponse} and the Created status.
     */
    @PostMapping("/create")
    public ResponseEntity<StudentEnrollmentResponse> processEnrollment(@RequestBody StudentEnrollmentRequest studentEnrollmentRequest) {
        StudentEnrollmentResponse response = studentEnrollmentService.enrollStudent(studentEnrollmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Handle HTTP request to update existed {@link StudentLicenseEnrollment} entity.
     *
     * @param enrollmentId The id of StudentEnrollment to be updated.
     * @param studentEnrollmentRequest The request containing the new data for enrollment.
     * @return responseEntity with  {@link StudentEnrollmentResponse}.
     */
    @PatchMapping("/update/{enrollmentId}")
    public ResponseEntity<StudentEnrollmentResponse> updateEnrollment(@PathVariable String enrollmentId, @RequestBody UpdateStudentEnrollmentRequest studentEnrollmentRequest) {
        StudentEnrollmentResponse response = studentEnrollmentService.updateEnrollment(enrollmentId, studentEnrollmentRequest);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Handle HTTP Get-request to get a StudentLicenseEnrollment entity by its id from the service layer.
     *
     * @param enrollmentId The id of StudentLicenseEnrollment to be returned.
     * @return responseEntity with StudentEnrollmentResponse.
     */
    @GetMapping("/{enrollmentId}")
    public ResponseEntity<StudentEnrollmentResponse> getById(@PathVariable String enrollmentId) {
        StudentEnrollmentResponse response = studentEnrollmentService.findById(enrollmentId);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Handle HTTP Get-Request to get all Enrollment of the DrivingSchool.
     * Specific number of Pages of StudentLicenseEnrollment will be returned from the service layer.
     *
     * @param page The pageNumber, which starts by zero.
     * @return ResponseEntity with the page of StudentLicenseEnrollmentResponse.
     */
    @GetMapping("/all")
    public ResponseEntity<PageResponse<StudentEnrollmentResponse>> getAll(@RequestParam int page) {
        PageResponse<StudentEnrollmentResponse> response = studentEnrollmentService.findAll(page);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Handle HTTP delete-request to delete StudentLicenseEnrollment entity.
     *
     * @param enrollmentId The id of entity to be deleted.
     */
    @DeleteMapping("/delete/{enrollmentId}")
    public void deleteById(@PathVariable String enrollmentId) {
        studentEnrollmentService.deleteEnrollment(enrollmentId);
    }

    /**
     * Handle HTTP Put-request to update the startDate of enrollment if it is not set at the creation stage
     * or need to be updated.
     *
     * @param enrollmentDateRequest The request with the new startDate of enrollment.
     * @return studentEnrollmentResponse with new startDate
     */
    @PutMapping("/update-start-date")
    public ResponseEntity<StudentEnrollmentResponse> updateStartDate(@RequestBody EnrollmentDateRequest enrollmentDateRequest) {
        StudentEnrollmentResponse response = studentEnrollmentService.setStartDate(enrollmentDateRequest);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Handle HTTP Put-request to update the CompleteDate of enrollment.
     *
     * @param enrollmentDateRequest The request with the new CompleteDate of enrollment.
     * @return studentEnrollmentResponse with new CompleteDate
     */
    @PutMapping("/update-complete-date")
    public ResponseEntity<StudentEnrollmentResponse> updateCompleteDate(@RequestBody EnrollmentDateRequest enrollmentDateRequest) {
        StudentEnrollmentResponse response = studentEnrollmentService.setCompletedDate(enrollmentDateRequest);
        return ResponseEntity.ok().body(response);
    }

    /**
     * Handle HTTP Put-request to update the CancelDate of enrollment.
     *
     * @param enrollmentDateRequest The request with the new CancelDate of enrollment.
     * @return studentEnrollmentResponse with new CancelDate.
     */
    @PutMapping("/update-cancel-date")
    public ResponseEntity<StudentEnrollmentResponse> updateCancelDate(@RequestBody EnrollmentDateRequest enrollmentDateRequest) {
        StudentEnrollmentResponse response = studentEnrollmentService.setCancelledDate(enrollmentDateRequest);
        return ResponseEntity.ok().body(response);
    }
}
