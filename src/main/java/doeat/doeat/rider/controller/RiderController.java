package doeat.doeat.rider.controller;

import doeat.doeat.rider.domain.Rider;
import doeat.doeat.rider.dto.RiderDto;
import doeat.doeat.rider.dto.request.CreateRiderRequestDto;
import doeat.doeat.rider.dto.request.UpdateRiderRequestDto;
import doeat.doeat.rider.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rider")
public class RiderController {
    private final RiderService riderService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody CreateRiderRequestDto requestDto){
        Rider rider = riderService.createRider(requestDto);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", rider);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("")
    public ResponseEntity findAll(){
        List<RiderDto> ridersDto = riderService.findRiderAll();

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", ridersDto);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateRiderRequestDto requestDto){

        RiderDto riderDto = riderService.updateRider(id, requestDto);

        String message = "변경 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", riderDto);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        riderService.deleteRiderById(id);

        String message = "삭제 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);

        return ResponseEntity.ok(responseBody);
    }
}
