package doeat.doeat.rider.dto.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateRiderRequestDto {

    private String name;
    private String phone;

    @Builder
    public CreateRiderRequestDto(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
}
