package com.zaljumaei.driveflow.student.repository;

import com.zaljumaei.driveflow.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, String> {

    Optional<Student> findByPersonDetails_Email(String email);
}
