package com.zaljumaei.driveflow.instructor.repository;

import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<DrivingSchool, Long> {
}
