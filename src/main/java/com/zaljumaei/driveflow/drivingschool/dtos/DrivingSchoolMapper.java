package com.zaljumaei.driveflow.drivingschool.dtos;

import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import org.springframework.stereotype.Component;

@Component
public class DrivingSchoolMapper {

    /**
     * mapp request to new entity DrivingSchool
     * @param drivingSchoolRequest
     * @return
     */
    public DrivingSchool toDrivingSchool(DrivingSchoolRequest drivingSchoolRequest){
        DrivingSchool drivingSchool = new DrivingSchool();
        drivingSchool.setName(drivingSchoolRequest.name());
        drivingSchool.setPhoneNumber(drivingSchoolRequest.phoneNumber());
        drivingSchool.setAddress(drivingSchoolRequest.address());

        return drivingSchool;
    }

    public DrivingSchoolResponse toDrivingSchoolResponse(DrivingSchool drivingSchool){
        return new DrivingSchoolResponse(
                drivingSchool.getId(),
                drivingSchool.getName(),
                drivingSchool.getPhoneNumber()
        );
    }

    /**
     * mapper to existing DrivingSchool
     * @param request with name, phoneNumber and Address
     * @param drivingSchool the DrivingSchool , that should be updated.
     * @return DrivingSchool after mapping.
     */
    public DrivingSchool updateMapperDrivingSchool(DrivingSchoolRequest request, DrivingSchool drivingSchool){
        drivingSchool.setName(request.name());
        drivingSchool.setPhoneNumber(request.phoneNumber());
        drivingSchool.setAddress(request.address());
        return drivingSchool;
    }
}
