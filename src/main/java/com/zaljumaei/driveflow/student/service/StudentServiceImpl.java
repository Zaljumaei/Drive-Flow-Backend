package com.zaljumaei.driveflow.student.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.common.PagingProps;
import com.zaljumaei.driveflow.student.domain.Student;
import com.zaljumaei.driveflow.student.dtos.CreateStudentRequest;
import com.zaljumaei.driveflow.student.dtos.StudentMapper;
import com.zaljumaei.driveflow.student.dtos.StudentResponse;
import com.zaljumaei.driveflow.student.dtos.UpdateStudentRequest;
import com.zaljumaei.driveflow.student.repository.StudentRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that implement {@link StudentService}
 */
@Slf4j
@Service
public class StudentServiceImpl implements  StudentService {

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    private final PagingProps pagingProps;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, PagingProps pagingProps) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.pagingProps = pagingProps;
    }

    /**
     * Create student entity from request.
     * The email should be unique.
     * @param request The request with all data
     * @return StudentResponse after created
     */
    @Override
    public StudentResponse create(CreateStudentRequest request) {
        checkIfStudentExistByEmail(request.email());
        Student student = studentMapper.toEntity(request);
        studentRepository.save(student);

        return studentMapper.toResponse(student);
    }

    @Override
    public StudentResponse findById(String id) {
        Student student = checkIfStudentExistById(id);

        return studentMapper.toResponse(student);
    }

    /**
     * Find all students of driving school, the number of students is specified in {@link PagingProps}.
     * @param page The number of page, it starts by zero.
     * @return student of the driving school.
     */
    @Override
    public PageResponse<StudentResponse> findAll(int page) {
        PageRequest pageRequest = PageRequest.of(page, pagingProps.getStudentPageSize());
        Page<Student> studentPage = studentRepository.findAll(pageRequest);

        return PageResponse.<StudentResponse>builder()
                .content(studentPage.getContent().stream().map(studentMapper::toResponse).collect(Collectors.toList()))
                .totalPages(studentPage.getTotalPages())
                .totalElement(studentPage.getNumberOfElements())
                .isFirst(studentPage.isFirst())
                .isLast(studentPage.isLast())
                .build();
    }

    /**
     * Update existed student entity by checking if it existed,
     * then delegate the update operation to {@link StudentMapper}.
     * @param id The id of the student
     * @param request The request with the new data
     * @return StudentResponse
     */
    @Override
    public StudentResponse update(String id, UpdateStudentRequest request) {
        Student student = checkIfStudentExistById(id);
        studentMapper.updateStudentFromRequest(request, student);
        studentRepository.save(student);
        return  studentMapper.toResponse(student);
    }

    /**
     * Delete existed student entity.
     * @param id The id of existed student.
     */
    @Override
    public void delete(String id) {
        Student student = checkIfStudentExistById(id);
        studentRepository.delete(student);
    }

    /**
     * To check if this email is already used by any student, to ensure that no such email is used.
     * An Exception will be thrown if the email is already used.
     * @param email The email to be checked
     */
    private void checkIfStudentExistByEmail(String email) {
        Optional<Student> student = studentRepository.findByPersonDetails_Email(email);
        if (student.isPresent()) {
            log.debug("Student with email {} is already exist", email);
            throw new EntityExistsException("Student with email " + email + " already exists");
        }
    }

    /**
     * Check if student exist by id
     * @param id the id of student
     * @return the founded student
     */
    private Student checkIfStudentExistById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            log.debug("Student with id {} does not exist", id);
            throw new EntityNotFoundException("Student with id " + id + " does not exist");
        }
        return student.get();
    }
}
