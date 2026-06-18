package org.example.driveflow.common;

import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.regex.Pattern;

@MappedSuperclass
@Getter
@Setter
public class BasePersonEntity {

    @Id
    private Long id;

    private String tenantId;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String phoneNumber;

    @Embedded
    private Address address;

    @CreationTimestamp
    private LocalDate creationDate;

    @UpdateTimestamp
    private LocalDate updateDate;
}
