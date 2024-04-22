package doeat.doeat.order.domain;

import doeat.doeat.delivery.domain.Delivery;
import doeat.doeat.recruitment.domain.Recruitment;
import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Column(name = "orderDate")
    private LocalDateTime orderDate;

    @Column(name = "status")
    private boolean status;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Delivery delivery;

    @Column(name = "totalPrice")
    private float totalPrice;

    @Embedded
    private Address address;

    @Builder
    public Order(Long id, Member member,
                 Recruitment recruitment,
                 LocalDateTime orderDate,
                 boolean status, Delivery delivery,
                 float totalPrice, Address address) {

        this.id = id;
        this.member = member;
        this.recruitment = recruitment;
        this.orderDate = orderDate;
        this.status = status;
        this.delivery = delivery;
        this.totalPrice = totalPrice;
        this.address = address;
    }
}
