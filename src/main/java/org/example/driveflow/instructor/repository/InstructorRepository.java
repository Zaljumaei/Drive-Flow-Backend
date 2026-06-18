package org.example.driveflow.instructor.repository;

import org.example.driveflow.drivingschool.domain.DrivingSchool;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<DrivingSchool, Long> {
}
