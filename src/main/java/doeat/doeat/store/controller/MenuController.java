package doeat.doeat.store.controller;

import doeat.doeat.store.domain.Menu;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.MenuDto;
import doeat.doeat.store.dto.StoreDto;
import doeat.doeat.store.dto.request.CreateMenuRequestDto;
import doeat.doeat.store.dto.request.CreateStoreRequestDto;
import doeat.doeat.store.dto.request.UpdateMenuRequestDto;
import doeat.doeat.store.dto.request.UpdateStoreRequestDto;
import doeat.doeat.store.service.MenuService;
import doeat.doeat.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody CreateMenuRequestDto requestDto){
        Menu menu = menuService.createMenu(requestDto);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", menu);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/{id}")
    public ResponseEntity getMenuById(@RequestBody Long id){
        MenuDto menu = menuService.findMenuById(id);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", menu);

        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateMenuRequestDto requestDto){
        Menu menu = menuService.updateMenu(id, requestDto);

        String message = "업데이트 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", menu);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStore(@PathVariable Long id) {
        menuService.deleteMenuById(id);

        String message = "삭제 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);

        return ResponseEntity.ok(responseBody);
    }
}
