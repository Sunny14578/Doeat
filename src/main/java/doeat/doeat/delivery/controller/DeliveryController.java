package doeat.doeat.delivery.controller;

import doeat.doeat.delivery.domain.Delivery;
import doeat.doeat.delivery.dto.DeliveryDto;
import doeat.doeat.delivery.dto.request.CreateDeliveryRequestDto;
import doeat.doeat.delivery.dto.request.UpdateDeliveryRequestDto;
import doeat.doeat.delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody CreateDeliveryRequestDto requestDto){
        Delivery delivery = deliveryService.createDelivery(requestDto);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", delivery);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("")
    public ResponseEntity findAll(){
        List<DeliveryDto> deliverysDto = deliveryService.findDeliveryAll();

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", deliverysDto);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateDeliveryRequestDto requestDto){
        DeliveryDto deliveryDto = deliveryService.updateDelivery(id, requestDto);

        String message = "변경 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", deliveryDto);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        deliveryService.deleteDeliveryById(id);

        String message = "삭제 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);

        return ResponseEntity.ok(responseBody);
    }
}
