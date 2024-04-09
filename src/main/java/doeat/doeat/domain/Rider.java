package doeat.doeat.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "riders")
@Getter
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
}
