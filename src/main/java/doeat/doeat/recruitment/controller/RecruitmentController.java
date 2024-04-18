package doeat.doeat.recruitment.controller;

import doeat.doeat.recruitment.domain.Recruitment;
import doeat.doeat.recruitment.dto.RecruitmentDto;
import doeat.doeat.recruitment.dto.request.CreateRecruitmentRequestDto;
import doeat.doeat.recruitment.dto.request.UpdateRecruitmentRequestDto;
import doeat.doeat.recruitment.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody CreateRecruitmentRequestDto requestDto){
        Recruitment recruitment = recruitmentService.createRecruitment(requestDto);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", recruitment);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("")
    public ResponseEntity findAll(){
        List<RecruitmentDto> recruitmentsDto = recruitmentService.findRecruitmentAll();

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", recruitmentsDto);

        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateRecruitmentRequestDto requestDto){
        Recruitment recruitment = recruitmentService.updateRecruitment(id, requestDto);

        String message = "성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);
        responseBody.put("content", recruitment);

        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        recruitmentService.deleteRecruitmentById(id);

        String message = "삭제 성공하였습니다.";

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("code", "00");
        responseBody.put("message", message);

        return ResponseEntity.ok(responseBody);
    }
}
