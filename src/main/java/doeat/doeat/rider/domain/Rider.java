package doeat.doeat.rider.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "riders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private boolean status;

    @Builder
    public Rider(Long id, String name, String phone, boolean status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public void update(String name, String phone, Boolean status) {
        if (name != null){
            this.name = name;
        }

        if (phone != null){
            this.phone = phone;
        }

        if (status != null){
            this.status = status;
        }
    }
}
