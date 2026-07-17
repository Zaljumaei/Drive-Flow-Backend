package org.example.driveflow.drivingschool.service;

import org.example.driveflow.common.Address;
import org.example.driveflow.drivingschool.domain.DrivingSchool;
import org.example.driveflow.drivingschool.dtos.DrivingSchoolMapper;
import org.example.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import org.example.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import org.example.driveflow.drivingschool.repository.DrivingSchoolRepository;

import jakarta.persistence.EntityExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DrivingSchoolService Unit Tests")
class DrivingSchoolServiceImplTest {

    @Mock
    private DrivingSchoolRepository drivingSchoolRepository;

    @Mock
    private DrivingSchoolMapper drivingSchoolMapper;

    @InjectMocks
    private DrivingSchoolServiceImpl drivingSchoolServiceImpl;

    private DrivingSchool testdrivingSchool;
    private DrivingSchoolRequest testdrivingSchoolRequest;
    private DrivingSchoolResponse testdrivingSchoolResponse;

    @BeforeEach
    void setUp(){
        final Address address =Address.builder()
                .city("Bochum")
                .country("DE")
                .street("HauptStr.")
                .streetNumber("1")
                .zip("44111").build();

        this.testdrivingSchoolRequest =
                new DrivingSchoolRequest("Max-Fahrschule", "2314 9988", address);

        this.testdrivingSchool = new DrivingSchool();
        this.testdrivingSchool.setAddress(address);
        this.testdrivingSchool.setPhoneNumber("2314 9988");
        this.testdrivingSchool.setName("Max-Fahrschule");

        this.testdrivingSchoolResponse =
                new DrivingSchoolResponse(1L, "Max-Fahrschule", "2314 9988");


    }

    @Nested
    @DisplayName("Create DrivingSchool Tests")
    class CreateDrivingSchoolTest {

        @Test
        @DisplayName("Create DrivingSchool successfully when there is no one is already existed.")
        void shouldCreateDrivingSchoolSuccessfully() {
            //that is given
            when(DrivingSchoolServiceImplTest.this.drivingSchoolRepository.findByName(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.name()))
                    .thenReturn(Optional.empty());

            when(DrivingSchoolServiceImplTest.this.drivingSchoolMapper.toDrivingSchool(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest))
                    .thenReturn(DrivingSchoolServiceImplTest.this.testdrivingSchool);
            when(DrivingSchoolServiceImplTest.this.drivingSchoolRepository.save(any(DrivingSchool.class)))
                    .thenReturn(DrivingSchoolServiceImplTest.this.testdrivingSchool);
            when(DrivingSchoolServiceImplTest.this.drivingSchoolMapper.toDrivingSchoolResponse(DrivingSchoolServiceImplTest.this.testdrivingSchool))
                    .thenReturn(DrivingSchoolServiceImplTest.this.testdrivingSchoolResponse);

            //when
            DrivingSchoolServiceImplTest.this.testdrivingSchoolResponse =  DrivingSchoolServiceImplTest.this.drivingSchoolServiceImpl.create(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest);

            //then
            assertEquals(DrivingSchoolServiceImplTest.this.testdrivingSchoolResponse.name(),
                    DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.name());
            assertEquals(DrivingSchoolServiceImplTest.this.testdrivingSchoolResponse.phoneNumber(),
                    DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.phoneNumber());

            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(1)).save(any(DrivingSchool.class));
            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(1))
                    .findByName(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.name());
        }

        @Test
        @DisplayName("Throw Exception by creating a DrivingSchool when one is already existed.")
        void shouldThrowExceptionWhenDrivingSchoolExists() {
            //given
            when(DrivingSchoolServiceImplTest.this.drivingSchoolRepository.findByName(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.name()))
                    .thenReturn(Optional.of(DrivingSchoolServiceImplTest.this.testdrivingSchool));

            //when and then
            final EntityExistsException existsException = assertThrows(EntityExistsException.class, () ->
                DrivingSchoolServiceImplTest.this.drivingSchoolServiceImpl.create(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest));

            assertNotNull(existsException);
            assertEquals("Driving school already exists", existsException.getMessage());
            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(1)).
                    findByName(DrivingSchoolServiceImplTest.this.testdrivingSchoolRequest.name());
            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(0)).save(any(DrivingSchool.class));
            verifyNoInteractions(DrivingSchoolServiceImplTest.this.drivingSchoolMapper);
        }

    }


}