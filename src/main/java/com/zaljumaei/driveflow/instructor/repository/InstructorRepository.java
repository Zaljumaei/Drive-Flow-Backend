package com.zaljumaei.driveflow.instructor.repository;

import com.zaljumaei.driveflow.instructor.domain.Instructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, String> {


//    Optional<Instructor> findByEmailAndTenantId(String email, Long tenantId);
//
//    Optional<Instructor> findByIdAndTenantId(Long id, Long tenantId);
//
//    Page<Instructor> findByDrivingSchool_Id( Long drivingSchoolId, Pageable pageable);
//
//    /**
//     * Delete a instructor of specific driving school,
//     * in case that  the same instructor works in multiple driving school
//     * @param tenantId Id of driving school, that this instructor belong to.
//     * @param instructor Instructor to be deleted.
//     */
//    void deleteByDrivingSchool_Id(Long tenantId, Instructor instructor);

}
