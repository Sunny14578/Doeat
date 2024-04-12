package doeat.doeat.store.service;

import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.request.CreateStoreRequestDto;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.dto.request.UpdateStoreRequestDto;
import doeat.doeat.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    // 가게 등록
    @Transactional
    public Store createStore(CreateStoreRequestDto createStoreDto){

        validateDuplicateStore(createStoreDto.getBusinessNumber());

        Store store = Store.builder()
                .businessNumber(createStoreDto.getBusinessNumber())
                .name(createStoreDto.getName())
                .address(createStoreDto.getAddress())
                .categories(createStoreDto.getCategories())
                .build();

        return storeRepository.save(store);
    }

    // 가게 전체 조회
    @Transactional
    public List<StoreDto> findStoreAll(){
        List<Store> stores = storeRepository.findAll();

        return mapToDtoList(stores);
    }

    // 가게 이름으로 조회
    @Transactional
    public List<StoreDto> findStoreName(String name){
        List<Store> stores = storeRepository.findStoresByNameContaining(name);

        return mapToDtoList(stores);
    }

    // 사업자번호로 조회
    @Transactional
    public StoreDto findByBusinessNumber(String businessNumber){
        Store store = storeRepository.findByBusinessNumber(businessNumber);

        StoreDto storeDto = StoreDto.builder()
                .businessNumber(store.getBusinessNumber())
                .name(store.getName())
                .address(store.getAddress())
                .categories(store.getCategories())
                .build();

        return storeDto;
    }

    // 가게 정보 변경
    @Transactional
    public void updateStore(UpdateStoreRequestDto updateStoreDto){
        Store findStore = storeRepository.findByBusinessNumber(updateStoreDto.getBusinessNumber());

        if (findStore == null) {
            throw new IllegalArgumentException("해당 가게가 존재하지 않습니다.");
        }

        findStore.update(
                updateStoreDto.getName(),
                updateStoreDto.getAddress(),
                updateStoreDto.getCategories()
        );
    }

    // entity > dto 변환
    private List<StoreDto> mapToDtoList(List<Store> stores) {
        List<StoreDto> storesDto = new ArrayList<>();

        for (Store store : stores) {
            StoreDto storeDto = StoreDto.builder()
                    .businessNumber(store.getBusinessNumber())
                    .name(store.getName())
                    .address(store.getAddress())
                    .categories(store.getCategories())
                    .point(store.getPoint())
                    .build();

            storesDto.add(storeDto);
        }

        return storesDto;
    }

    // 사업자번호로 중복가게 검증
    private void validateDuplicateStore(String businessNumber){
        Optional<Store> findStore = Optional.ofNullable(storeRepository.findByBusinessNumber(businessNumber));

        if (findStore.isPresent()) {
            throw new IllegalStateException("이미 존재하는 사업자등록번호입니다.");
        }
    }


}
