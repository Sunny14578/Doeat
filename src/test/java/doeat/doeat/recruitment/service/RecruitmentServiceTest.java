package doeat.doeat.recruitment.service;

import doeat.doeat.member.domain.Address;
import doeat.doeat.recruitment.domain.Recruitment;
import doeat.doeat.recruitment.dto.RecruitmentDto;
import doeat.doeat.recruitment.dto.request.CreateRecruitmentRequestDto;
import doeat.doeat.recruitment.dto.request.UpdateRecruitmentRequestDto;
import doeat.doeat.recruitment.repository.RecruitmentRepository;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class RecruitmentServiceTest {

    @Autowired RecruitmentService recruitmentService;
    @Autowired RecruitmentRepository recruitmentRepository;
    @Autowired StoreRepository storeRepository;

    @Test
    public void 모집등록() throws Exception{
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        CreateRecruitmentRequestDto createRecruitmentRequestDto = CreateRecruitmentRequestDto.builder()
                .store(store)
                .startTime(LocalDateTime.now())
                .build();

        // when
        Recruitment recruitment = recruitmentService.createRecruitment(createRecruitmentRequestDto);

        // then
        assertNotNull(recruitment.getId());

    }

    @Test
    public void 참여찾기() throws Exception{
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        storeRepository.save(store);

        Recruitment recruitment1 = Recruitment.builder()
                .store(store)
                .startTime(LocalDateTime.now())
                .build();

        Recruitment recruitment2 = Recruitment.builder()
                .store(store)
                .startTime(LocalDateTime.now())
                .build();

        recruitmentRepository.save(recruitment1);
        recruitmentRepository.save(recruitment2);

        // when
        List<RecruitmentDto> recruitments = recruitmentService.findRecruitmentAll();

        // then
        assertEquals(2, recruitments.size());
    }

    @Test
    public void 참여변경() throws Exception{
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        Recruitment recruitment = Recruitment.builder()
                .store(store)
                .startTime(LocalDateTime.now())
                .build();

        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        UpdateRecruitmentRequestDto updatedRecruitment = UpdateRecruitmentRequestDto.builder()
                .status(true)
                .build();

        // when
        recruitmentService.updateRecruitment(savedRecruitment.getId(), updatedRecruitment);

        // then
        Optional<Recruitment> findRecruitment = recruitmentRepository.findById(savedRecruitment.getId());
        Recruitment recruitmentTest = findRecruitment.orElseThrow(IllegalArgumentException::new);

        assertTrue(recruitmentTest.isStatus());
    }

    @Test
    public void 참여삭제() throws Exception{
        // given
        Store store = Store.builder()
                .businessNumber("111-11-11111")
                .name("테스트이름")
                .address(Address.builder().build())
                .categories(new ArrayList<>())
                .build();

        Recruitment recruitment = Recruitment.builder()
                .store(store)
                .startTime(LocalDateTime.now())
                .build();

        Recruitment savedRecruitment = recruitmentRepository.save(recruitment);

        // when
        recruitmentService.deleteRecruitmentById(savedRecruitment.getId());

        // then
        Optional<Recruitment> deletedRecruitmentOptional = recruitmentRepository.findById(savedRecruitment.getId());
        assertFalse(deletedRecruitmentOptional.isPresent());
    }
}
