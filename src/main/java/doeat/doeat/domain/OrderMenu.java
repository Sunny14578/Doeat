package doeat.doeat.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "orderMenus")
@Getter
public class OrderMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "price")
    private float price;

    @Column(name = "count")
    private int count;
}
