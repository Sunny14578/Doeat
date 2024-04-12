package doeat.doeat.store.domain;

import doeat.doeat.member.domain.Address;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stores")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "businessNumber")
    private String businessNumber;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "store")
    List<StoreCategories> categories = new ArrayList<>();

    @Column(name = "point")
    private Long point;

    @Builder
    public Store(Long id, String businessNumber, String name, Address address, List<StoreCategories> categories, Long point) {
        this.id = id;
        this.businessNumber = businessNumber;
        this.name = name;
        this.address = address;
        this.categories = categories;
        this.point = point;
    }

    public void update(String name, Address address, List<StoreCategories> categories) {
        if (name != null) {
            this.name = name;
        }
        if (address != null) {
            this.address = address;
        }
        if (categories != null) {
            this.categories = categories;
        }
    }




}
