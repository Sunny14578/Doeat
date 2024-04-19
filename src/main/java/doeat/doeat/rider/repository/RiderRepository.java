package doeat.doeat.rider.repository;

import doeat.doeat.rider.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
