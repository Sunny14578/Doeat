package doeat.doeat.store.domain;

import doeat.doeat.member.domain.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "menus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private doeat.doeat.store.domain.Store Store;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private boolean status;

    @Builder
    public Menu(Long id, String name, doeat.doeat.store.domain.Store store, String explanation, float price, boolean status) {
        this.id = id;
        this.name = name;
        this.Store = store;
        this.explanation = explanation;
        this.price = price;
        this.status = status;
    }

    public void update(String name, String explanation, Float price, Boolean status) {
        if (name != null) {
            this.name = name;
        }
        if (explanation != null) {
            this.explanation = explanation;
        }
        if (price != null) {
            this.price = price;
        }
        if (status != null) {
            this.status = status;
        }
    }
}
