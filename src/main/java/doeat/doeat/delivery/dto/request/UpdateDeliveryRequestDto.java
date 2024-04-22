package doeat.doeat.delivery.dto.request;

import doeat.doeat.rider.domain.Rider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateDeliveryRequestDto {
    private Rider rider;
    private String name;
    private boolean status;

    @Builder
    public UpdateDeliveryRequestDto(Rider rider, String name, boolean status) {
        this.rider = rider;
        this.name = name;
        this.status = status;
    }
}
