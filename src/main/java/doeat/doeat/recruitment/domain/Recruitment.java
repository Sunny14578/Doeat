package doeat.doeat.recruitment.domain;

import doeat.doeat.store.domain.Store;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recruitments")
@Getter
public class Recruitment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "startTime")
    private LocalDateTime startTime;

    @Column(name = "status")
    private boolean status;

    @Builder
    public Recruitment(Long id, Store store, LocalDateTime startTime, boolean status) {
        this.id = id;
        this.store = store;
        this.startTime = startTime;
        this.status = status;
    }

    public void update(Boolean status){
        if (status != null){
            this.status = status;
        }
    }
}
