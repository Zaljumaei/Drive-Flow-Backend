package com.zaljumaei.driveflow.drivingschool.dtos;

import com.zaljumaei.driveflow.common.Address;

public record DrivingSchoolRequest(String name,
                                   String phoneNumber,
                                   Address address
        ) { }
