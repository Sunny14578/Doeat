package doeat.doeat.delivery.domain;

import doeat.doeat.rider.domain.Rider;
import doeat.doeat.order.domain.Order;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliverys")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    @Builder
    public Delivery(Long id, Order order, Rider rider, String name, boolean status) {
        this.id = id;
        this.order = order;
        this.rider = rider;
        this.name = name;
        this.status = status;
    }

    public void update(Rider rider, String name, Boolean status) {
        if (rider != null){
            this.rider = rider;
        }

        if (name != null){
            this.name = name;
        }

        if (status != null){
            this.status = status;
        }
    }
}
