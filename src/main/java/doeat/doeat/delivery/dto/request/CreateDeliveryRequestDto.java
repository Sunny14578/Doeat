package doeat.doeat.delivery.dto.request;

import doeat.doeat.order.domain.Order;
import doeat.doeat.rider.domain.Rider;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateDeliveryRequestDto {
    private Order order;
    private Rider rider;
    private String name;

    @Builder
    public CreateDeliveryRequestDto(Order order, Rider rider, String name) {
        this.order = order;
        this.rider = rider;
        this.name = name;
    }
}
