package doeat.doeat.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "menus")
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store Store;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private boolean status;

}
