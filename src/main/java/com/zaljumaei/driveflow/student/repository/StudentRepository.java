package com.zaljumaei.driveflow.student.repository;

import com.zaljumaei.driveflow.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
