package doeat.doeat.repository;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.request.CreateStoreRequestDto;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.dto.request.UpdateStoreRequestDto;
import doeat.doeat.store.repository.StoreRepository;
import doeat.doeat.store.service.StoreService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class StoreServiceTest {

    @Autowired StoreRepository storeRepository;
    @Autowired StoreService storeService;
    @Autowired EntityManager em;

    @Test
    public void 가게등록() throws Exception{
        // given
        CreateStoreRequestDto storedto = CreateStoreRequestDto.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        // when
        Store store = storeService.createStore(storedto);

        // then
        assertEquals(store, storeRepository.findByBusinessNumber("111-11-11111"));
    }

    @Test
    public void 중복가게검증() throws Exception{

        // given
        CreateStoreRequestDto store1 = CreateStoreRequestDto.builder()
                .businessNumber("111-11-11111")
                .build();

        CreateStoreRequestDto store2 = CreateStoreRequestDto.builder()
                .businessNumber("111-11-11111")
                .build();

        // when
        storeService.createStore(store1);

        // then
        assertThrows(IllegalStateException.class, () -> {
            storeService.createStore(store2);
        });
    }

    @Test
    public void 가게정보변경() {
        // given
        String businessNumber = "111-11-11111";

        Store store = Store.builder()
                .businessNumber(businessNumber)
                .name("가게1")
                .address(Address.builder().street("main1").city("City1").zipCode("12345").build())
                .categories(new ArrayList<>())
                .build();

        storeRepository.save(store);

        UpdateStoreRequestDto updatedStore = UpdateStoreRequestDto.builder()
//                .name("가게2")
                .address(Address.builder().street("main2").city("City2").zipCode("54321").build())
                .categories(new ArrayList<>())
                .build();

        // when
        storeService.updateStore(store.getId(), updatedStore);

        // then
        Optional<Store> findStore = storeRepository.findById(store.getId());
        Store storeTest = findStore.orElseThrow(IllegalArgumentException::new);

        assertEquals(Objects.requireNonNullElse(updatedStore.getName(), store.getName()), storeTest.getName());
        assertEquals(Objects.requireNonNullElse(updatedStore.getAddress().getStreet(), store.getAddress().getStreet()), storeTest.getAddress().getStreet());
        assertEquals(Objects.requireNonNullElse(updatedStore.getAddress().getCity(), store.getAddress().getCity()), storeTest.getAddress().getCity());
        assertEquals(Objects.requireNonNullElse(updatedStore.getAddress().getZipCode(), store.getAddress().getZipCode()), storeTest.getAddress().getZipCode());
    }

    @Test
    public void 사업자번호로찾기() {
        // given
        String businessNumber = "111-11-11111";
        Store store = Store.builder()
                .businessNumber(businessNumber)
                .name("가게1")
                .address(Address.builder().street("main1").city("City1").zipCode("12345").build())
                .categories(new ArrayList<>())
                .build();

        storeRepository.save(store);

        // When
        StoreDto storeDto = storeService.findByBusinessNumber(businessNumber);

        // Then
        assertEquals(store.getBusinessNumber(), storeDto.getBusinessNumber());
        assertEquals(store.getName(), storeDto.getName());
        assertEquals(store.getAddress(), storeDto.getAddress());
        assertEquals(store.getCategories(), storeDto.getCategories());
    }
}
