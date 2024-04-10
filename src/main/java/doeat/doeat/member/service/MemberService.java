package doeat.doeat.member.service;

import doeat.doeat.member.domain.Member;
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
    public void createMember(CreateMemberDto createMemberDto) {
        if(memberRepository.existsByName(createMemberDto.getName())) throw new IllegalArgumentException();

        Member newMember = Member.builder()
                .name(createMemberDto.getName())
                .address(createMemberDto.getAddress())
                .role(createMemberDto.getRole())
                .password(createMemberDto.getPassword()).build();

        memberRepository.save(newMember);
    }

    @Transactional
    public MemberDto findMemberById(Long memberId) {
        Optional<Member> findUser = memberRepository.findById(memberId);
        Member member = findUser.orElseThrow(IllegalArgumentException::new);
        return MemberDto.builder()
                .address(member.getAddress())
                .role(member.getRole())
                .name(member.getName())
                .build();
    }
}
