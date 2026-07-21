package com.zaljumaei.driveflow.common;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Embeddable
@Builder
@AllArgsConstructor
public class Address {

    private String country;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String street;
    private String streetNumber;

    public Address() {

    }
}
