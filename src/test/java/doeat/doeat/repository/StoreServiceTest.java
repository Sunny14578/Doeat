package doeat.doeat.repository;

import doeat.doeat.member.domain.Address;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.CreateStoreDto;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.repository.StoreRepository;
import doeat.doeat.store.service.StoreService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
        CreateStoreDto storedto = CreateStoreDto.builder()
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
        CreateStoreDto store1 = CreateStoreDto.builder()
                .businessNumber("111-11-11111")
                .build();

        CreateStoreDto store2 = CreateStoreDto.builder()
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

        Store updatedStore = Store.builder()
                .businessNumber(businessNumber)
                .name("가게2")
                .address(Address.builder().street("main2").city("City2").zipCode("54321").build())
                .categories(new ArrayList<>())
                .build();

        // when
        storeService.updateStore(businessNumber, updatedStore);

        // then
        Store findStore = storeRepository.findByBusinessNumber(businessNumber);
        assertEquals(updatedStore.getName(), findStore.getName());
        assertEquals(updatedStore.getAddress().getStreet(), findStore.getAddress().getStreet());
        assertEquals(updatedStore.getAddress().getCity(), findStore.getAddress().getCity());
        assertEquals(updatedStore.getAddress().getZipCode(), findStore.getAddress().getZipCode());
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
