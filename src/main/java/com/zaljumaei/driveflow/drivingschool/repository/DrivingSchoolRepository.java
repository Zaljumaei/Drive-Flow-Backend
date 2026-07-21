package com.zaljumaei.driveflow.drivingschool.repository;

import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrivingSchoolRepository extends JpaRepository<DrivingSchool, Long>, CustomizedDrivingSchoolRepository {


    Optional<DrivingSchool> findByName(String drivingSchoolName);
}
