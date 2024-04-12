package doeat.doeat.member.dto.request;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    private String name;
    private String password;
    private Role role;
    private String street;
    private String city;
    private String zipCode;

    @Builder
    private SignupRequestDto(String name, String password, Role role, String street, String city, String zipCode) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
}
