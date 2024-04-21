package doeat.doeat.recruitment.service;

import doeat.doeat.recruitment.domain.Recruitment;
import doeat.doeat.recruitment.dto.RecruitmentDto;
import doeat.doeat.recruitment.dto.request.CreateRecruitmentRequestDto;
import doeat.doeat.recruitment.dto.request.UpdateRecruitmentRequestDto;
import doeat.doeat.recruitment.repository.RecruitmentRepository;
import doeat.doeat.store.domain.Store;
import doeat.doeat.store.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional
    public Recruitment createRecruitment(CreateRecruitmentRequestDto requestDto){

        Recruitment recruitment = Recruitment.builder()
                .store(requestDto.getStore())
                .startTime(requestDto.getStartTime())
                .status(requestDto.isStatus())
                .build();

        return recruitmentRepository.save(recruitment);
    }

    @Transactional
    public List<RecruitmentDto> findRecruitmentAll(){
        List<Recruitment> recruitments = recruitmentRepository.findAll();

        return mapToDtoList(recruitments);
    }

    @Transactional
    public Recruitment updateRecruitment(Long id, UpdateRecruitmentRequestDto requestDto){
        Optional<Recruitment> findRecruitment = recruitmentRepository.findById(id);
        Recruitment recruitment = findRecruitment.orElseThrow(IllegalArgumentException::new);

        recruitment.update(
                requestDto.isStatus()
        );

        return recruitment;
    }

    @Transactional
    public void deleteRecruitmentById(Long id){
        recruitmentRepository.deleteById(id);
    }

    private List<RecruitmentDto> mapToDtoList(List<Recruitment> recruitments) {
        List<RecruitmentDto> recruitmentsDto = new ArrayList<>();

        for (Recruitment recruitment : recruitments) {
            RecruitmentDto recruitmentDto = RecruitmentDto.builder()
                    .store(recruitment.getStore())
                    .startTime(recruitment.getStartTime())
                    .status(recruitment.isStatus())
                    .build();

            recruitmentsDto.add(recruitmentDto);
        }

        return recruitmentsDto;
    }
}
