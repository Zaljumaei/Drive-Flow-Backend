package com.zaljumaei.driveflow.student.repository;

import com.zaljumaei.driveflow.student.domain.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByPersonDetails_Email(String email);

    @Query("""
    SELECT s FROM Student s
    WHERE s.drivingSchool.id = :tenantId
            AND (
                LOWER(s.personDetails.firstName) LIKE LOWER(CONCAT('%' , :query, '%') )
                OR LOWER(s.personDetails.lastName) LIKE LOWER(CONCAT('%', :query, '%') )    
                )
    ORDER BY  s.personDetails.lastName, s.personDetails.firstName           
    """)
    Page<Student> searchStudents(
            @Param("tenantId") String tenantId,
            @Param("query") String query,
            Pageable pageable
    );
}
