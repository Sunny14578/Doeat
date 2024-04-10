package doeat.doeat.member.service;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Member;
import doeat.doeat.member.dto.AddressDto;
import doeat.doeat.member.dto.CreateMemberDto;
import doeat.doeat.member.dto.MemberDto;
import doeat.doeat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long createMember(CreateMemberDto createMemberDto) {
        if (memberRepository.existsByName(createMemberDto.getName())) throw new IllegalArgumentException();

        Member newMember = Member.builder()
                .name(createMemberDto.getName())
                .address(createMemberDto.getAddress())
                .role(createMemberDto.getRole())
                .password(createMemberDto.getPassword()).build();
        return memberRepository.save(newMember).getId();
    }

    public MemberDto findMemberById(Long memberId) {
        Optional<Member> findUser = memberRepository.findById(memberId);
        Member member = findUser.orElseThrow(IllegalArgumentException::new);
        return MemberDto.builder()
                .address(member.getAddresses())
                .role(member.getRole())
                .name(member.getName())
                .build();
    }

    @Transactional
    public void addAddress(Long memberId, AddressDto newAddressDto) {
        Address newAddress = Address.dtoToAddress(newAddressDto);

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(IllegalArgumentException::new);
        member.getAddresses().add(newAddress);
        memberRepository.save(member);
    }

    @Transactional
    public void deleteAddress(Long memberId, AddressDto addressToDeleteDto) {
        // 사용자 엔티티 조회
        Address addressToDelete = Address.dtoToAddress(addressToDeleteDto);

        Optional<Member> optionalMember = memberRepository.findById(memberId);
        Member member = optionalMember.orElseThrow(IllegalArgumentException::new);
        member.getAddresses().removeIf(address -> address.equals(addressToDelete));
        memberRepository.save(member);
    }
}
