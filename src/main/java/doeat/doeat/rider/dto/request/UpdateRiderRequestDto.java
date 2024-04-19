package doeat.doeat.rider.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateRiderRequestDto {

    private String name;
    private String phone;
    private boolean status;

    @Builder
    public UpdateRiderRequestDto(String name, String phone, boolean status) {
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
