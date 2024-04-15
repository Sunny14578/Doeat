package doeat.doeat.store.repository;


import doeat.doeat.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findStoresByNameContaining(String name);
    Store findByBusinessNumber(String businessNumber);
}
