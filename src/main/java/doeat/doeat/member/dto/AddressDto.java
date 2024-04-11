package doeat.doeat.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AddressDto {
    private String street;
    private String city;
    private String zipCode;

    @Builder
    private AddressDto(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}
