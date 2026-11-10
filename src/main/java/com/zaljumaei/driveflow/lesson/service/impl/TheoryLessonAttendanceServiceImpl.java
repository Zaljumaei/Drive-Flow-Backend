package com.zaljumaei.driveflow.lesson.service.impl;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.lesson.domain.TheoryLessonAttendance;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceMapper;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceRequest;
import com.zaljumaei.driveflow.lesson.dto.TheoryLessonAttendance.TheoryLessonAttendanceResponse;
import com.zaljumaei.driveflow.lesson.repository.TheoryLessonAttendanceRepository;
import com.zaljumaei.driveflow.lesson.service.TheoryLessonAttendanceService;
import com.zaljumaei.driveflow.lesson.service.TheoryLessonService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TheoryLessonAttendanceServiceImpl implements TheoryLessonAttendanceService {

    private final TheoryLessonAttendanceRepository attendanceRepository;
    private final TheoryLessonAttendanceMapper attendanceMapper;
    private final TheoryLessonService theoryLessonService;

    /**
     * Create a new theory lesson attendance.
     *
     * @param request The attendance request.
     * @return The created attendance response.
     */
    @Override
    public TheoryLessonAttendanceResponse create(
            TheoryLessonAttendanceRequest request
    ) {

        TheoryLessonAttendance attendance =
                attendanceMapper.toEntity(request);

        checkForDuplicateAttendance(attendance, null);

        attendance.setConfirmedAt(LocalDateTime.now());

        TheoryLessonAttendance savedAttendance =
                attendanceRepository.save(attendance);

        return attendanceMapper.toResponse(savedAttendance);
    }

    /**
     * Find an attendance by id and return its response.
     *
     * @param id The attendance id.
     * @return The attendance response.
     */
    @Override
    public TheoryLessonAttendanceResponse findById(String id) {

        TheoryLessonAttendance attendance = checkIfExistById(id);

        return attendanceMapper.toResponse(attendance);
    }

    /**
     * Find all attendances with pagination.
     *
     * @param pageNumber Page number.
     * @return A page of attendance responses.
     */
    @Override
    public PageResponse<TheoryLessonAttendanceResponse> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 14);

        Page<TheoryLessonAttendance> attendances = attendanceRepository.findAll(pageable);

        return  PageResponse.<TheoryLessonAttendanceResponse>builder()
                .content(
                        attendances.getContent()
                                .stream()
                                .map(attendanceMapper::toResponse)
                                .toList()
                )
                .totalPages(attendances.getTotalPages())
                .totalElement(attendances.getNumberOfElements())
                .isFirst(attendances.isFirst())
                .isLast(attendances.isLast())
                .build();
    }

    @Override
    public PageResponse<TheoryLessonAttendanceResponse> findByTheoryLessonId(String theoryLessonId, int pageNumber) {
        theoryLessonService.findEntityById(theoryLessonId);

        Pageable pageable = PageRequest.of(pageNumber, 14);

        Page<TheoryLessonAttendance> attendances =
                attendanceRepository.findByTheoryLesson_Id(
                        theoryLessonId,
                        pageable
                );
        return  PageResponse.<TheoryLessonAttendanceResponse>builder()
                .content(
                        attendances.getContent()
                                .stream()
                                .map(attendanceMapper::toResponse)
                                .toList()
                )
                .totalPages(attendances.getTotalPages())
                .totalElement(attendances.getNumberOfElements())
                .isFirst(attendances.isFirst())
                .isLast(attendances.isLast())
                .build();
    }


    /**
     * Update an existing attendance.
     *
     * @param id The attendance id.
     * @param request The updated attendance data.
     * @return The updated attendance response.
     */
    @Override
    public TheoryLessonAttendanceResponse update(
            String id,
            TheoryLessonAttendanceRequest request
    ) {

        TheoryLessonAttendance attendance = checkIfExistById(id);

        attendanceMapper.updateEntity(request, attendance);

        checkForDuplicateAttendance(attendance, id);

        return attendanceMapper.toResponse(attendance);
    }

    /**
     * Delete an attendance by id.
     *
     * @param id The attendance id.
     */
    @Override
    public void delete(String id) {

        TheoryLessonAttendance attendance = checkIfExistById(id);

        attendanceRepository.delete(attendance);
    }

    /**
     * Find an attendance entity by id.
     *
     * @param id The attendance id.
     * @return The existing attendance entity.
     */
    @Override
    public TheoryLessonAttendance findEntityById(String id) {

        return checkIfExistById(id);
    }

    /**
     * Check if an attendance exists by id.
     * If not, an exception will be thrown.
     *
     * @param id The attendance id.
     * @return The existing attendance entity.
     */
    private TheoryLessonAttendance checkIfExistById(String id) {
        Optional<TheoryLessonAttendance> attendance =
                attendanceRepository.findById(id);

        if (attendance.isEmpty()) {
            throw new EntityNotFoundException("Theory lesson attendance with id " + id + " does not exist");
        }
        return attendance.get();
    }

    /**
     * Prevent duplicate attendance records for the same
     * student and theory lesson.
     *
     * @param attendance The attendance to validate.
     * @param currentId The current attendance id, or null for creation.
     */
    private void checkForDuplicateAttendance(
            TheoryLessonAttendance attendance,
            String currentId
    ) {

        String lessonId = attendance.getTheoryLesson().getId();
        String studentId = attendance.getStudentLicenseEnrollment()
                .getStudent()
                .getId();

        boolean exists;

        if (currentId == null) {
            exists = attendanceRepository
                    .existsByTheoryLesson_IdAndStudentLicenseEnrollment_Student_Id(
                            lessonId,
                            studentId
                    );
        } else {
            exists = attendanceRepository
                    .existsByTheoryLesson_IdAndStudentLicenseEnrollment_Student_IdAndIdNot(
                            lessonId,
                            studentId,
                            currentId
                    );
        }

        if (exists) {
            throw new EntityExistsException("Attendance already exists for student " + studentId + " in theory lesson " + lessonId);
        }
    }


}
