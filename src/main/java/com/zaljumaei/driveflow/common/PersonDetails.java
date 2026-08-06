package com.zaljumaei.driveflow.common;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PersonDetails {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Email
    private String email;

    private String phoneNumber;

    @Embedded
    private Address address;

    public PersonDetails() {

    }
}
