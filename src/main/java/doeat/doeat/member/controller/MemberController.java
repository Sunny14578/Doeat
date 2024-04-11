package doeat.doeat.member.controller;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.dto.AddressDto;
import doeat.doeat.member.dto.CreateMemberDto;
import doeat.doeat.member.dto.MemberDto;
import doeat.doeat.member.dto.request.SignupRequestDto;
import doeat.doeat.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long memberId) {
        MemberDto memberDto = memberService.findMemberById(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(memberDto);
    }

    @PostMapping("/signup")
    public ResponseEntity<Long> signUp(@RequestBody SignupRequestDto requestDto) {
        CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .role(requestDto.getRole())
                .address(Address.builder()
                        .city(requestDto.getCity())
                        .street(requestDto.getStreet())
                        .zipCode(requestDto.getZipCode())
                        .build())
                .name(requestDto.getName())
                .password(requestDto.getPassword()).build();
        Long memberId = memberService.createMember(createMemberDto);// 회원가입 로직은 UserService에서 구현되어 있다고 가정
        return ResponseEntity.status(HttpStatus.CREATED).body(memberId);
    }

    @PostMapping("/{memberId}/addresses")
    public ResponseEntity<String> addAddressToMember(@PathVariable Long memberId, @RequestBody AddressDto newAddress) {
        memberService.addAddress(memberId, newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body("Address added successfully");
    }

    @DeleteMapping("/{memberId}/addresses")
    public ResponseEntity<String> deleteAddressFromMember(@PathVariable Long memberId, @RequestBody AddressDto addressToDelete) {
        memberService.deleteAddress(memberId, addressToDelete);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
