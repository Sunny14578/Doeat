package doeat.doeat.member.dto;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
public class MemberDto {
    private String name;
    private Role role;
    private List<Address> addresses;

    @Builder
    private MemberDto(String name, Role role, List<Address> address) {
        this.name = name;
        this.role = role;
        this.addresses = address;
    }
}
