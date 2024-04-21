package doeat.doeat.rider.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RiderDto {

    private Long id;
    private String name;
    private String phone;
    private boolean status;

    @Builder
    public RiderDto(Long id, String name, String phone, boolean status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }
}
