package doeat.doeat.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "deliverys")
@Getter
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
}
