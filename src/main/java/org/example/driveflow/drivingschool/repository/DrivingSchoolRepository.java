package org.example.driveflow.drivingschool.repository;

import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Long>, CustomizedDrivingSchoolRepository {


    Optional<DrivingSchool> findByName(String drivingSchoolName);
}
