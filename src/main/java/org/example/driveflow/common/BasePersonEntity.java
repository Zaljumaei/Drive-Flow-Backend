package org.example.driveflow.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class BasePersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
    @Column(name = "creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @LastModifiedDate
    @Column(name = "update_date", nullable = false, updatable = true)
    private LocalDate updateDate;
}
