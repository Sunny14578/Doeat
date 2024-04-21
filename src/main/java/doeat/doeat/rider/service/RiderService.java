package doeat.doeat.rider.service;

import doeat.doeat.rider.domain.Rider;
import doeat.doeat.rider.dto.RiderDto;
import doeat.doeat.rider.dto.request.CreateRiderRequestDto;
import doeat.doeat.rider.dto.request.UpdateRiderRequestDto;
import doeat.doeat.rider.repository.RiderRepository;
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
public class RiderService {
    private final RiderRepository riderRepository;

    @Transactional
    public Rider createRider(CreateRiderRequestDto requestDto) {
        Rider rider = Rider.builder()
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .build();

        return riderRepository.save(rider);
    }

    @Transactional
    public List<RiderDto> findRiderAll(){
        List<Rider> riders = riderRepository.findAll();

        return mapToDtoList(riders);
    }

    @Transactional
    public RiderDto updateRider(Long id, UpdateRiderRequestDto requestDto) {
        Optional<Rider> findRider = riderRepository.findById(id);
        Rider rider = findRider.orElseThrow(IllegalArgumentException::new);

        rider.update(
                requestDto.getName(),
                requestDto.getPhone(),
                requestDto.isStatus()
        );

        RiderDto riderDto = RiderDto.builder()
                .id(rider.getId())
                .name(rider.getName())
                .phone(rider.getPhone())
                .status(rider.isStatus())
                .build();

        return riderDto;
    }

    @Transactional
    public void deleteRiderById(Long id){
        riderRepository.deleteById(id);
    }

    private List<RiderDto> mapToDtoList(List<Rider> riders) {
        List<RiderDto> ridersDto = new ArrayList<>();

        for (Rider rider : riders){
            RiderDto riderDto = RiderDto.builder()
                    .name(rider.getName())
                    .phone(rider.getPhone())
                    .status(rider.isStatus())
                    .build();

            ridersDto.add(riderDto);
        }

        return ridersDto;
    }
}
