package org.example.driveflow.drivingschool.repository;

import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Long>, CustomizedDrivingSchoolRepository {


}
