package com.zaljumaei.driveflow.student.enrollment;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.common.PagingProps;
import com.zaljumaei.driveflow.licenseclass.LicenseClass;
import com.zaljumaei.driveflow.licenseclass.LicenseClassRepository;
import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.student.enrollment.dto.*;
import com.zaljumaei.driveflow.student.repository.StudentRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Service class to handle enrollment of student,
 * when student register for, start or cancel driving licence class.
 */
@Slf4j
@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final LicenseClassRepository licenseClassRepository;
    private final StudentEnrollmentMapper studentEnrollmentMapper;
    private final PagingProps pagingProps;

    public StudentEnrollmentServiceImpl(StudentEnrollmentRepository studentEnrollmentRepository,
                                        StudentRepository studentRepository,
                                        LicenseClassRepository licenseClassRepository,
                                        StudentEnrollmentMapper studentEnrollmentMapper,
                                        PagingProps pagingProps) {
        this.studentEnrollmentRepository = studentEnrollmentRepository;
        this.studentRepository = studentRepository;
        this.licenseClassRepository = licenseClassRepository;
        this.studentEnrollmentMapper = studentEnrollmentMapper;
        this.pagingProps = pagingProps;
    }

    /**
     * Register a student for license class from the request.
     *
     * @param enrollmentRequest The request, that hold data for registration process.
     * @return {@link StudentEnrollmentResponse} after save the entity in DB.
     */
    public StudentEnrollmentResponse enrollStudent(StudentEnrollmentRequest enrollmentRequest) {
        Student student = studentRepository.findById(enrollmentRequest.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student with id: " + enrollmentRequest.studentId() + " not found"));

        LicenseClass licenseClass = licenseClassRepository.findById(enrollmentRequest.licenseClassId())
                .orElseThrow(() -> new EntityNotFoundException("Drive Class with id: " + enrollmentRequest.licenseClassId() + " not found"));

        StudentLicenseEnrollment studentLicenseEnrollment = studentEnrollmentMapper.toEntity(student, licenseClass, enrollmentRequest);

        studentEnrollmentRepository.save(studentLicenseEnrollment);

        return studentEnrollmentMapper.toResponse(studentLicenseEnrollment);
    }


    /**
     * Find specific number of pages of StudentEnrollments to perform the performance,
     * the number can be set in the properties, default one is set on {@link PagingProps}
     *
     * @param page number of page, it starts by zero.
     * @return PageResponse of the founded enrollments.
     */
    public PageResponse<StudentEnrollmentResponse> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, pagingProps.getStudentEnrollmentPageSize());

        Page<StudentLicenseEnrollment> enrollments = studentEnrollmentRepository.findAll(pageRequest);

        return PageResponse.<StudentEnrollmentResponse>builder()
                .content(enrollments.getContent().stream().map(studentEnrollmentMapper::toResponse).collect(Collectors.toList()))
                .totalPages(enrollments.getTotalPages())
                .isLast(enrollments.isLast())
                .isFirst(enrollments.isFirst())
                .build();
    }
    /**
     * Service methode to update an enrollment, to allow student change his enrollment, like license class.
     * For updating just the start-, complete-, or cancel-date, use the other methods.
     *
     * @param enrollmentId The id of enrollment to be updated.
     * @param request The update request containing the new data.
     * @return response of enrollment after be updated.
     */
    public StudentEnrollmentResponse updateEnrollment(String enrollmentId, UpdateStudentEnrollmentRequest request) {
        StudentLicenseEnrollment studentLicenseEnrollment = checkIfStudentLicenseEnrollmentExists(enrollmentId);

        if(!request.licenseClassId().isBlank()){
            LicenseClass licenseClass = licenseClassRepository.findById(request.licenseClassId())
                    .orElseThrow(() -> new EntityNotFoundException("Drive Class with id: " + request.licenseClassId() + " not found"));
            studentLicenseEnrollment.setLicenseClass(licenseClass);
        }

        if(!request.studentEnrollmentStatus().isBlank()){
            studentLicenseEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.valueOf( request.studentEnrollmentStatus()));
        }

        if (request.startDate() != null){
            LocalDate startDate = checkIfDateAfterRegistration(request.startDate(), studentLicenseEnrollment.getStartDate());

            studentLicenseEnrollment.setStartDate(startDate);
            studentLicenseEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.ACTIVE);
        }

        if (!request.notes().isBlank()){
            studentLicenseEnrollment.setNotes(request.notes());
        }
        studentEnrollmentRepository.save(studentLicenseEnrollment);

        return  studentEnrollmentMapper.toResponse(studentLicenseEnrollment);
    }

    /**
     * Find a {@link StudentLicenseEnrollment} entity by its id, if it's founded.
     *
     * @param enrollmentId The id of studentEnrollment
     * @return studentEnrollmentResponse
     */
    public StudentEnrollmentResponse findById(String enrollmentId) {
        StudentLicenseEnrollment studentLicenseEnrollment = checkIfStudentLicenseEnrollmentExists(enrollmentId);
        return studentEnrollmentMapper.toResponse(studentLicenseEnrollment);
    }

    /**
     * Delete a existed student-enrollment
     *
     * @param enrollmentId The id of enrollment to be deleted.
     */
    public void deleteEnrollment(String enrollmentId) {
        StudentLicenseEnrollment studentLicenseEnrollment = checkIfStudentLicenseEnrollmentExists(enrollmentId);
        studentEnrollmentRepository.delete(studentLicenseEnrollment);
    }

    /**
     * Set the start date of the enrollment, when the student decide to start his drivingLicense classes.
     *
     * @param request The request containing the start date.
     * @return response with the new information about the enrollment.
     */
    @Override
    public StudentEnrollmentResponse setStartDate(EnrollmentDateRequest request) {
        StudentLicenseEnrollment studentEnrollment = checkIfStudentLicenseEnrollmentExists(request.studentEnrollmentId());

        LocalDate startDate = checkIfDateAfterRegistration(request.date(), studentEnrollment.getStartDate());

        studentEnrollment.setStartDate(startDate);
        studentEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.ACTIVE);
        if(!request.notes().isBlank()){
            studentEnrollment.setNotes(request.notes());
        }

        studentEnrollmentRepository.save(studentEnrollment);

        return studentEnrollmentMapper.toResponse(studentEnrollment);
    }

    /**
     * Set the date of completion the driving license class.
     *
     * @param request The request containing the complete date.
     * @return response with the new information about the enrollment.
     */
    @Override
    public StudentEnrollmentResponse setCompletedDate(EnrollmentDateRequest request) {
        StudentLicenseEnrollment studentEnrollment = checkIfStudentLicenseEnrollmentExists(request.studentEnrollmentId());

        LocalDate complete = checkIfDateAfterRegistration(request.date(), studentEnrollment.getRegistrationDate());

        studentEnrollment.setCompletedDate(complete);
        studentEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.COMPLETED);
        if(!request.notes().isBlank()){
            studentEnrollment.setNotes(request.notes());
        }
        studentEnrollmentRepository.save(studentEnrollment);

        return studentEnrollmentMapper.toResponse(studentEnrollment);
    }

    /**
     * Set the cancellation date of enrollment.
     * And change the enrollment status to CANCELED.
     *
     * @param request The request containing the cancellation date.
     * @return response with the new information about the enrollment.
     */
    @Override
    public StudentEnrollmentResponse setCancelledDate(EnrollmentDateRequest request) {
        StudentLicenseEnrollment studentLicenseEnrollment = checkIfStudentLicenseEnrollmentExists(request.studentEnrollmentId());

        LocalDate cancelDate = checkIfDateAfterRegistration(request.date(), studentLicenseEnrollment.getRegistrationDate());

        studentLicenseEnrollment.setCancelledDate(cancelDate);
        studentLicenseEnrollment.setStudentLicenseStatus(StudentEnrollmentLicenseStatus.CANCELLED);
        if(!request.notes().isBlank()){
            studentLicenseEnrollment.setNotes(request.notes());
        }
        studentEnrollmentRepository.save(studentLicenseEnrollment);

        return studentEnrollmentMapper.toResponse(studentLicenseEnrollment);
    }

    /**
     * Helper methode to check if the StudentEnrollment entity is existed.
     *
     * @param id The id of enrollment entity.
     * @return existed enrollment entity.
     */
    private StudentLicenseEnrollment checkIfStudentLicenseEnrollmentExists(String id) {
        return studentEnrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("StudentEnrollment with id: " + id + " not found"));

    }

    /**
     * Check if the start-, complete, or canceled date after registration date,
     * or throw an exception.
     *
     * @param date The start-, complete, or canceled date.
     * @param registrationDate The enrollment registration date.
     * @return The start-, complete, or canceled date if it valid.
     */
    private LocalDate checkIfDateAfterRegistration(LocalDate date, LocalDate registrationDate) {
        if(date.isAfter(registrationDate)){
            return date;
        }
        log.debug("Invalid date after registration date, date: {}, registrationDate: {}", date, registrationDate);
        throw new IllegalArgumentException("Registration date must before date");
    }

}
