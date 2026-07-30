package com.zaljumaei.driveflow.instructor.dtos;

import com.zaljumaei.driveflow.instructor.domain.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    /**
     * Map request to Instructor object.
     * @param instructorRequest request that contains all data for instructor
     * @return Instructor object after filling it with data.
     */
    public Instructor toEntity(CreateInstructorRequest instructorRequest){
        Instructor instructor = new Instructor();
        instructor.setFirstName(instructorRequest.firstName());
        instructor.setLastName(instructorRequest.lastName());
        instructor.setEmail(instructorRequest.email());
        instructor.setAddress(instructorRequest.address());
        instructor.setPhoneNumber(instructorRequest.phoneNumber());
        instructor.setBirthDate(instructorRequest.birthDate());
        return instructor;
    }

    /**
     * Map Instructor object to InstructorResponse.
     * @param instructor object that will map to Response.
     * @return InstructorResponse.
     */
    public InstructorResponse toInstructorResponse(Instructor instructor){
        return InstructorResponse.builder()
                .id(instructor.getId())
                .firstName(instructor.getFirstName())
                .lastName(instructor.getLastName())
                .email(instructor.getEmail())
                .build();
    }

    public void updatedInstructorFromRequest(UpdateInstructorRequest request,Instructor instructor){
        if (request.firstName() != null) {
            instructor.setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            instructor.setLastName(request.lastName());
        }

        if (request.birthDate() != null) {
            instructor.setBirthDate(request.birthDate());
        }

        if (request.phoneNumber() != null) {
            instructor.setPhoneNumber(request.phoneNumber());
        }

        if (request.address() != null) {
            instructor.setAddress(request.address());
        }

        if (request.email() != null) {
            instructor.setEmail(request.email());
        }
    }
}
