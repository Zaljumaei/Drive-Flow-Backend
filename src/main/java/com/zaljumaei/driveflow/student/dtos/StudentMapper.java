package com.zaljumaei.driveflow.student.dtos;

import com.zaljumaei.driveflow.common.PersonDetails;
import com.zaljumaei.driveflow.student.domain.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    /**
     * Map request to student entity
     * @param request CreateRequest with all needed data
     * @return created Student entity
     */
    public Student toEntity(CreateStudentRequest request) {
        Student student = new Student();
        PersonDetails personDetails = PersonDetails.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .birthDate(request.birthDate())
                .phoneNumber(request.phoneNumber())
                .address(request.address())
                .build();
        student.setPersonDetails(personDetails);
        if (!request.licenseClass().isEmpty()){
            student.setLicenseClass(request.licenseClass());
        }
        return student;
    }

    /**
     * Map a student entity to response
     * @param student Entity to be mapped
     * @return Student Response
     */
    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .firstName(student.getPersonDetails().getFirstName())
                .lastName(student.getPersonDetails().getLastName())
                .email(student.getPersonDetails().getEmail())
                .build();
    }

    /**
     * Update existed student entity with the new data
     * @param request The request with the new data
     * @param student The entity to be updated
     */
    public void updateStudentFromRequest(UpdateStudentRequest request, Student student) {
        if (request.firstName() != null) {
            student.getPersonDetails().setFirstName(request.firstName());
        }

        if (request.lastName() != null) {
            student.getPersonDetails().setLastName(request.lastName());
        }

        if (request.birthDate() != null) {
            student.getPersonDetails().setBirthDate(request.birthDate());
        }

        if (request.phoneNumber() != null) {
            student.getPersonDetails().setPhoneNumber(request.phoneNumber());
        }

        if (request.address() != null) {
            student.getPersonDetails().setAddress(request.address());
        }

        if (request.email() != null) {
            student.getPersonDetails().setEmail(request.email());
        }

        if (!request.licenseClass().isEmpty()) {
            student.setLicenseClass(request.licenseClass());
        }

    }
}
