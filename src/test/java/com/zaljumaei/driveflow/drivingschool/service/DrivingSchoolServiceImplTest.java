package com.zaljumaei.driveflow.drivingschool.service;

import com.zaljumaei.driveflow.common.Address;
import com.zaljumaei.driveflow.drivingschool.domain.DrivingSchool;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolMapper;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolRequest;
import com.zaljumaei.driveflow.drivingschool.dtos.DrivingSchoolResponse;
import com.zaljumaei.driveflow.drivingschool.repository.DrivingSchoolRepository;

import jakarta.persistence.EntityNotFoundException;
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

    private DrivingSchool testDrivingSchool;
    private DrivingSchoolRequest testDrivingSchoolRequest;
    private DrivingSchoolResponse testDrivingSchoolResponse;

    @BeforeEach
    void setUp(){
        final Address address =Address.builder()
                .city("Bochum")
                .country("DE")
                .street("HauptStr.")
                .streetNumber("1")
                .zip("44111").build();

        this.testDrivingSchoolRequest =
                new DrivingSchoolRequest("Max-Fahrschule", "2314 9988", address);

        this.testDrivingSchool = new DrivingSchool();
        this.testDrivingSchool.setId("1");
        this.testDrivingSchool.setAddress(address);
        this.testDrivingSchool.setPhoneNumber("2314 9988");
        this.testDrivingSchool.setName("Max-Fahrschule");

        this.testDrivingSchoolResponse =
                new DrivingSchoolResponse("1", "Max-Fahrschule", "2314 9988");


    }

    @Nested
    @DisplayName("Create DrivingSchool Tests")
    class CreateDrivingSchoolTest {

        @Test
        @DisplayName("Create DrivingSchool successfully when there is no one is already existed.")
        void shouldCreateDrivingSchoolSuccessfully() {
            //that is given
            when(drivingSchoolRepository.findByName(testDrivingSchoolRequest.name()))
                    .thenReturn(Optional.empty());

            when(drivingSchoolMapper.toDrivingSchool(testDrivingSchoolRequest))
                    .thenReturn(testDrivingSchool);
            when(drivingSchoolRepository.save(any(DrivingSchool.class)))
                    .thenReturn(testDrivingSchool);
            when(drivingSchoolMapper.toDrivingSchoolResponse(testDrivingSchool))
                    .thenReturn(testDrivingSchoolResponse);

            //when
           testDrivingSchoolResponse =  drivingSchoolServiceImpl.create(testDrivingSchoolRequest);

            //then
            assertEquals(testDrivingSchoolResponse.name(),
                    testDrivingSchoolRequest.name());
            assertEquals(testDrivingSchoolResponse.phoneNumber(),
                    testDrivingSchoolRequest.phoneNumber());

            verify(drivingSchoolRepository, times(1)).save(any(DrivingSchool.class));
            verify(drivingSchoolRepository, times(1))
                    .findByName(testDrivingSchoolRequest.name());
        }

        @Test
        @DisplayName("Throw Exception by creating a DrivingSchool when one is already existed.")
        void shouldThrowExceptionWhenDrivingSchoolExists() {
            //given
            when(drivingSchoolRepository.findByName(DrivingSchoolServiceImplTest.this.testDrivingSchoolRequest.name()))
                    .thenReturn(Optional.of(DrivingSchoolServiceImplTest.this.testDrivingSchool));

            //when and then
            final EntityExistsException existsException = assertThrows(EntityExistsException.class, () ->
                DrivingSchoolServiceImplTest.this.drivingSchoolServiceImpl.create(DrivingSchoolServiceImplTest.this.testDrivingSchoolRequest));

            assertNotNull(existsException);
            assertEquals("Driving school already exists", existsException.getMessage());
            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(1)).
                    findByName(DrivingSchoolServiceImplTest.this.testDrivingSchoolRequest.name());
            verify(DrivingSchoolServiceImplTest.this.drivingSchoolRepository, times(0)).save(any(DrivingSchool.class));
            verifyNoInteractions(drivingSchoolMapper);
        }

    }

    @Nested
    @DisplayName("Update DrivingSchool Tests")
    class UpdateDrivingSchoolTest {

        @Test
        @DisplayName("Should update existing DrivingSchool successfully")
        void shouldUpdateDrivingSchoolSuccessfully() {
            // given
            String drivingSchoolId = testDrivingSchool.getId();

            DrivingSchool updatedDrivingSchool = new DrivingSchool();
            updatedDrivingSchool.setId(drivingSchoolId);
            updatedDrivingSchool.setAddress(testDrivingSchool.getAddress());
            updatedDrivingSchool.setName("Neu-Max-Fahrschule");
            updatedDrivingSchool.setPhoneNumber("2314 4455");

            DrivingSchoolResponse expectedResponse = new DrivingSchoolResponse(
                    drivingSchoolId,
                    updatedDrivingSchool.getName(),
                    updatedDrivingSchool.getPhoneNumber()
            );

            when(drivingSchoolRepository.findById(drivingSchoolId))
                    .thenReturn(Optional.of(testDrivingSchool));

            when(drivingSchoolMapper.updateMapperDrivingSchool(
                    testDrivingSchoolRequest,
                    testDrivingSchool
            )).thenReturn(updatedDrivingSchool);

            when(drivingSchoolRepository.save(updatedDrivingSchool))
                    .thenReturn(updatedDrivingSchool);

            when(drivingSchoolMapper.toDrivingSchoolResponse(updatedDrivingSchool))
                    .thenReturn(expectedResponse);

            // when
            DrivingSchoolResponse actualResponse =
                    drivingSchoolServiceImpl.update(drivingSchoolId, testDrivingSchoolRequest);

            // then
            assertEquals(expectedResponse, actualResponse);

            verify(drivingSchoolRepository).findById(drivingSchoolId);
            verify(drivingSchoolMapper).updateMapperDrivingSchool(
                    testDrivingSchoolRequest,
                    testDrivingSchool
            );
            verify(drivingSchoolRepository).save(updatedDrivingSchool);
            verify(drivingSchoolMapper).toDrivingSchoolResponse(updatedDrivingSchool);
        }

        @Test
        @DisplayName("Should throw exception when DrivingSchool is not found")
        void shouldThrowExceptionWhenEntityNotFound() {
            // given
            String drivingSchoolId = testDrivingSchool.getId();

            when(drivingSchoolRepository.findById(drivingSchoolId))
                    .thenReturn(Optional.empty());

            // when
            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> drivingSchoolServiceImpl.update(drivingSchoolId, testDrivingSchoolRequest)
            );

            // then
            assertEquals(
                    "No driving school found with id " + drivingSchoolId,
                    exception.getMessage()
            );

            verify(drivingSchoolRepository).findById(drivingSchoolId);
            verifyNoInteractions(drivingSchoolMapper);
            verify(drivingSchoolRepository, never()).save(any(DrivingSchool.class));
        }
    }

    @Nested
    @DisplayName("Delete DrivingSchool Tests")
    class DeleteDrivingSchoolTest {

        @Test
        @DisplayName("should delete DrivingSchool entity successfully")
        void shouldDeleteDrivingSchoolSuccessfully() {
            String drivingSchoolId = testDrivingSchool.getId();
            when(drivingSchoolRepository.findById(drivingSchoolId)).thenReturn(Optional.of(testDrivingSchool));

            assertDoesNotThrow(() -> drivingSchoolServiceImpl.delete(drivingSchoolId));
            verify(drivingSchoolRepository, times(1)).delete(testDrivingSchool);
        }

        @Test
        @DisplayName("should throw Exception when Entity not found.")
        void shouldThrowExceptionWhenEntityNotFound() {
            String drivingSchoolId = testDrivingSchool.getId();
            when(drivingSchoolRepository.findById(drivingSchoolId)).thenReturn(Optional.empty());
            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> drivingSchoolServiceImpl.delete(drivingSchoolId));

            assertNotNull(exception);
            verify(drivingSchoolRepository, never()).delete(testDrivingSchool);
        }
    }

    @Nested
    @DisplayName("Find DrivingSchool ById Tests")
    class FindDrivingSchoolByIdTest {

        @Test
        @DisplayName("Should return DrivingSchool when id exists")
        void shouldReturnDrivingSchoolWhenIdExists() {
            // given
            String id = testDrivingSchool.getId();

            when(drivingSchoolRepository.findById(id))
                    .thenReturn(Optional.of(testDrivingSchool));

            when(drivingSchoolMapper.toDrivingSchoolResponse(testDrivingSchool))
                    .thenReturn(testDrivingSchoolResponse);

            // when
            DrivingSchoolResponse response = drivingSchoolServiceImpl.findById(id);

            // then
            assertEquals(testDrivingSchoolResponse, response);

            verify(drivingSchoolRepository).findById(id);
            verify(drivingSchoolMapper).toDrivingSchoolResponse(testDrivingSchool);
        }

        @Test
        @DisplayName("Should throw exception when DrivingSchool is not found")
        void shouldThrowExceptionWhenDrivingSchoolNotFound() {
            // given
            String id = "99L";

            when(drivingSchoolRepository.findById(id))
                    .thenReturn(Optional.empty());

            // when
            EntityNotFoundException exception = assertThrows(
                    EntityNotFoundException.class,
                    () -> drivingSchoolServiceImpl.findById(id)
            );

            // then
            assertEquals("No driving school found with id " + id, exception.getMessage());

            verify(drivingSchoolRepository).findById(id);
            verifyNoInteractions(drivingSchoolMapper);
        }
    }


}