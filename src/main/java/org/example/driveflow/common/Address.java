package org.example.driveflow.common;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String country;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String street;
    private String streetNumber;

}
