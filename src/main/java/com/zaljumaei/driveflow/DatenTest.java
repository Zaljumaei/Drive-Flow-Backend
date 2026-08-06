package com.zaljumaei.driveflow;

import com.github.javafaker.Faker;
import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolMapper;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.repository.DrivingSchoolRepository;
import com.zaljumaei.driveflow.drivingschool.service.DrivingSchoolService;
import com.zaljumaei.driveflow.tenantmanagement.TenantContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DatenTest implements CommandLineRunner {

    private Faker faker = new Faker();

    private DrivingSchool  drivingSchool;

    @Autowired
    private DrivingSchoolMapper drivingSchoolMapper;

    @Autowired
    private DrivingSchoolRepository drivingSchoolRepository;

    @Override
    public void run(String... args) throws Exception {
        List<DrivingSchoolRequest> requests = IntStream.rangeClosed(1,5)
                .mapToObj(i -> new DrivingSchoolRequest(
                        faker.name().name().concat(" Fahrschule"),
                        faker.phoneNumber().cellPhone(),
                        Address.builder()
                                .state(faker.address().state())
                                .city(faker.address().city())
                                .streetNumber(faker.address().streetAddressNumber())
                                .street(faker.address().streetName())
                                .build()
                )).toList();

        TenantContext.setCurrentTenant("DEFAULT");
        List<DrivingSchool> drSchools = requests.stream().map(drivingSchoolMapper::toDrivingSchool).toList();
        drSchools.forEach(school -> drivingSchoolRepository.save(school));

    }
}
