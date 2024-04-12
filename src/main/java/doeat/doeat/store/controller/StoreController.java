package doeat.doeat.store.controller;

import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.dto.request.CreateStoreRequestDto;
import doeat.doeat.store.dto.request.UpdateStoreRequestDto;
import doeat.doeat.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody CreateStoreRequestDto requestDto){
        Store store = storeService.createStore(requestDto);

        String message = "회원가입에 성공하였습니다.";
        String response = "{\"message\": \"" + message + "\", \"storeId\": " + store.getId() + "}";
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{name}")
    public ResponseEntity getStoreByName(@RequestBody String requestName){
        List<StoreDto> stores = storeService.findStoreName(requestName);

        return ResponseEntity.status(HttpStatus.OK).body(stores);
    }

    @PutMapping("/{businessNumber}")
    public ResponseEntity update(@RequestBody UpdateStoreRequestDto requestDto){
        storeService.updateStore(requestDto);

        return ResponseEntity.ok("업데이트에 성공하였습니다.");
    }
}
