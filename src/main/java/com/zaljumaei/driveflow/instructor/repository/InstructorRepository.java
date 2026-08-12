package com.zaljumaei.driveflow.instructor.repository;

import com.zaljumaei.driveflow.instructor.domain.Instructor;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorRepository extends JpaRepository<Instructor, String> {


     Optional<Instructor> findByPersonDetails_Email(String email);

     Optional<Instructor> findById(String id);
}
