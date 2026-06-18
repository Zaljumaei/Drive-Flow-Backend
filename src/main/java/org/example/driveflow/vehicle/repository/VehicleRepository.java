package org.example.driveflow.vehicle.repository;

import org.example.driveflow.vehicle.domain.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
