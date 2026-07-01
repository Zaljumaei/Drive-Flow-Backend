package org.example.driveflow.drivingschool.dtos;

import org.example.driveflow.common.Address;

public record DrivingSchoolRequest(String name,
                                   String phoneNumber,
                                   Address address
        ) { }
