package com.zaljumaei.driveflow.instructor.dtos;

import com.zaljumaei.driveflow.common.PersonDetails;
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
        PersonDetails personDetails = PersonDetails.builder()
                .firstName(instructorRequest.firstName())
                .lastName(instructorRequest.lastName())
                .email(instructorRequest.email())
                .birthDate(instructorRequest.birthDate())
                .address(instructorRequest.address())
                .phoneNumber(instructorRequest.phoneNumber())
                .build();

        instructor.setPersonDetails(personDetails);
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
                .firstName(instructor.getPersonDetails().getFirstName())
                .lastName(instructor.getPersonDetails().getLastName())
                .email(instructor.getPersonDetails().getEmail())
                .build();
    }

    /**
     * Update existed instructor entity from request.
     * @param request The request with new data
     * @param instructor The entity to be updated.
     */
    public void updatedInstructorFromRequest(UpdateInstructorRequest request,Instructor instructor){
        if (request.firstName() != null) {
            instructor.getPersonDetails().setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            instructor.getPersonDetails().setLastName(request.lastName());
        }

        if (request.birthDate() != null) {
            instructor.getPersonDetails().setBirthDate(request.birthDate());
        }

        if (request.phoneNumber() != null) {
            instructor.getPersonDetails().setPhoneNumber(request.phoneNumber());
        }

        if (request.address() != null) {
            instructor.getPersonDetails().setAddress(request.address());
        }

        if (request.email() != null) {
            instructor.getPersonDetails().setEmail(request.email());
        }
    }
}
