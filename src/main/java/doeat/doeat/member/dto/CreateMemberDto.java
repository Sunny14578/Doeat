package doeat.doeat.member.dto;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateMemberDto {
    private String name;
    private Role role;
    private Address address;
    private String password;

    @Builder
    private CreateMemberDto(String name, Role role, Address address, String password) {
        this.name = name;
        this.role = role;
        this.address = address;
        this.password = password;
    }
}
