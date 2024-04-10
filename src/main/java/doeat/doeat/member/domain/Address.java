package doeat.doeat.member.domain;

import doeat.doeat.member.dto.AddressDto;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Address {
    private String street;
    private String city;
    private String zipCode;

    public static Address dtoToAddress(AddressDto addressDto) {
        return Address.builder()
                .city(addressDto.getCity())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode()).build();
    }

    @Builder
    private Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}