package doeat.doeat.member.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String zipCode;
}