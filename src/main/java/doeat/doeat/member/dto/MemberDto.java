package doeat.doeat.member.dto;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Role;
import lombok.Builder;
import lombok.Getter;


@Getter
public class MemberDto {
    private String name;
    private Role role;
    private Address address;

    @Builder
    private MemberDto(String name, Role role, Address address) {
        this.name = name;
        this.role = role;
        this.address = address;
    }
}
