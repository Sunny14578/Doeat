package doeat.doeat.delivery.dto;

import doeat.doeat.order.domain.Order;
import doeat.doeat.rider.domain.Rider;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DeliveryDto {
    private Long id;
    private Order order;
    private Rider rider;
    private String name;
    private boolean status;

    @Builder
    public DeliveryDto(Long id, Order order, Rider rider, String name, boolean status) {
        this.id = id;
        this.order = order;
        this.rider = rider;
        this.name = name;
        this.status = status;
    }
}
