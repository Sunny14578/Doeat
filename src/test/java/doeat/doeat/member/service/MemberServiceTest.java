package doeat.doeat.member.service;

import doeat.doeat.member.domain.Address;
import doeat.doeat.member.domain.Member;
import doeat.doeat.member.domain.Role;
import doeat.doeat.member.dto.AddressDto;
import doeat.doeat.member.dto.CreateMemberDto;
import doeat.doeat.member.dto.MemberDto;
import doeat.doeat.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.groups.Tuple.*;


@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void afterEach() {
        memberRepository.deleteAllInBatch();
    }

    @DisplayName("성공적으로 회원가입을 합니다")
    @Test
    public void createMember() throws Exception {
        //given
        CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .name("seungsu")
                .password("1234")
                .address(Address.builder()
                        .street("거리")
                        .zipCode("123456")
                        .city("도시").build()
                ).role(Role.ROLE_USER).build();

        //when,then
        Long member = memberService.createMember(createMemberDto);
    }

    @DisplayName("중복된 이름으로 회원가입을 합니다 - 예외발생")
    @Test
    public void createMember_with_duplicateName() throws Exception {
        //given
        String duplicateName = "seungsu";
        memberRepository.save(Member.builder().name(duplicateName).build());

        CreateMemberDto createMemberDto = CreateMemberDto.builder()
                .name(duplicateName)
                .password("1234")
                .address(Address.builder()
                        .street("거리")
                        .zipCode("123456")
                        .city("도시").build()
                ).role(Role.ROLE_USER).build();

        //when,then
        Assertions.assertThatThrownBy(() -> memberService.createMember(createMemberDto)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("id로 멤버를 찾습니다")
    @Test
    @Transactional
    public void findMemberById() throws Exception {
        //given
        Member member = Member.builder().name("멤버").role(Role.ROLE_USER).build();
        memberRepository.save(member);
        Long id = member.getId();

        //when
        MemberDto memberDto = memberService.findMemberById(id);

        //then
        Assertions.assertThat(memberDto.getName()).isEqualTo(member.getName());
    }

    @DisplayName("멤버의 새로운 주소를 추가합니다")
    @Test
    @Transactional
    public void addAddress() throws Exception {
        //given
        Member member = Member.builder().name("멤버").build();
        memberRepository.save(member);

        AddressDto addressDto = AddressDto.builder()
                .street("거리")
                .zipCode("123456")
                .city("도시").build();
        //when
        memberService.addAddress(member.getId(),addressDto);
        List<Address> addresses = member.getAddresses();
        //then
        Assertions.assertThat(addresses).extracting("street", "city", "zipCode")
                .containsExactly(tuple("거리", "도시", "123456"));
    }

    @DisplayName("멤버의 기존 주소를 삭제합니다")
    @Test
    @Transactional
    public void deleteAddress() throws Exception {
        //given
        AddressDto addressDto = AddressDto.builder()
                .street("거리")
                .zipCode("123456")
                .city("도시").build();

        Member member = Member.builder()
                .name("멤버")
                .address(Address.dtoToAddress(addressDto))
                .build();
        memberRepository.save(member);

        //when
        memberService.deleteAddress(member.getId(),addressDto);

        //then
        Assertions.assertThat(member.getAddresses().size()).isEqualTo(0);
    }
}