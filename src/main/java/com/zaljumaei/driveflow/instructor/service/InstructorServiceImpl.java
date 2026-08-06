package com.zaljumaei.driveflow.instructor.service;

import com.zaljumaei.driveflow.common.PageResponse;
import com.zaljumaei.driveflow.common.PagingProps;
import com.zaljumaei.driveflow.instructor.domain.Instructor;
import com.zaljumaei.driveflow.instructor.dtos.*;
import com.zaljumaei.driveflow.instructor.repository.InstructorRepository;
import com.zaljumaei.driveflow.tenantmanagement.TenantContext;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service class for Instructor
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;

    private final InstructorMapper instructorMapper;

    private final PagingProps props;

    /**
     * Service methode to create new {@link Instructor} Entity
     * by the data provided in dto {@link CreateInstructorRequest},
     * all data in request should be provided.
     * @param request The Instructor data
     * @return Dto response
     */
    @Override
    public InstructorResponse create(CreateInstructorRequest request) {
        //Long tenantId = TenantContext.getCurrentTenant();
        //checkIfExistByEmail(request.email(), tenantId);

        Instructor instructor = instructorMapper.toEntity(request);
        instructorRepository.save(instructor);
        InstructorResponse response = instructorMapper.toInstructorResponse(instructor);
        return response;
    }

    /**
     * Service method to update existed {@link Instructor} entity
     * @param id The id of instructor to be updated
     * @param request The dto with new data.
     * @return Dto response after updating.
     */
    @Override
    public InstructorResponse update(String id, UpdateInstructorRequest request) {
        //Long tenantId = TenantContext.getCurrentTenant();
        Instructor instructor = checkIfExistById(id);

        instructorMapper.updatedInstructorFromRequest(request, instructor);
        instructorRepository.save(instructor);
        InstructorResponse response = instructorMapper.toInstructorResponse(instructor);
        return response;
    }

    /**
     * Find an instructor with the provided id.
     * @param id The id of instructor.
     * @return Dto response.
     */
    @Override
    public InstructorResponse findById(String id) {
        //Long tenantId = TenantContext.getCurrentTenant();
        Instructor instructor = checkIfExistById(id);
        InstructorResponse response = instructorMapper.toInstructorResponse(instructor);
        return response;
    }

    /**
     * Find number of instructors of the driving school.
     * The number of instructors is specified in {@link PagingProps}
     * @return number of  PageResponse that include Instructors and other information.
     */
    @Override
    public PageResponse<InstructorResponse> findAll(int pageNumber) {
        //Long tenantId = TenantContext.getCurrentTenant();
        Pageable pageable = PageRequest.of(pageNumber, props.getInstructorPageSize());
        Page<Instructor> instructors = instructorRepository.findAll(pageable);

        return PageResponse.<InstructorResponse>builder()
                .content(instructors.getContent().stream().map(instructorMapper::toInstructorResponse).collect(Collectors.toList()))
                .totalPages(instructors.getTotalPages())
                .totalElement(instructors.getNumberOfElements())
                .isFirst(instructors.isFirst())
                .isLast(instructors.isLast())
                .build();
        //return null;
    }

    /**
     * Instructor can be existed by multiple driving school,
     * thus we have to ensure that it only be removed from this driving school.
     * @param id The instructor id.
     */
    @Override
    public void delete(String id) {
        //Long tenantId = TenantContext.getCurrentTenant();
        Instructor instructor = checkIfExistById(id);
       //instructorRepository.deleteByDrivingSchool_Id(tenantId, instructor);
    }

    //-------------------------------Helper Methods-------------------------------

    /**
     * check if instructor with this email is already existed and throw an exception.
     * This method can be used to ensure that no such email is used.
     * @param email The email of instructor
     * @param tenantId The id of driving school
     */
    private void checkIfExistByEmail(String email, Long tenantId) {
        /*Optional<Instructor> instructor = instructorRepository.findByEmailAndTenantId(email, tenantId);
        if (instructor.isPresent()) {
            log.debug("Instructor with email {} already exists", email);
            throw new EntityExistsException("Instructor with email " + email + " already exists");
        }*/
    }

    /**
     * Check if instructor exist by id, if not an exception will be thrown.
     * This method is used to ensure that an instructor with this id is existed.
     * @param id The instructor id .

     * @return existed instructor.
     */
    private Instructor checkIfExistById(String id) {
        /*Optional<Instructor> instructor = instructorRepository.findByIdAndTenantId(id,  tenantId);

        if (instructor.isEmpty()) {
            log.debug("Instructor with id {} does not exist", id);
            throw new EntityNotFoundException("Instructor with id " + id + " does not exist");
        }

        return instructor.get();*/

        return null;
    }

}
