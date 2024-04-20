package doeat.doeat.rider.service;

import doeat.doeat.rider.domain.Rider;
import doeat.doeat.rider.dto.RiderDto;
import doeat.doeat.rider.dto.request.CreateRiderRequestDto;
import doeat.doeat.rider.dto.request.UpdateRiderRequestDto;
import doeat.doeat.rider.repository.RiderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class RiderServiceTest {

    @Autowired RiderService riderService;
    @Autowired RiderRepository riderRepository;

    @Test
    public void 배달기사등록() throws Exception{
        // given
        CreateRiderRequestDto createRiderRequestDto = CreateRiderRequestDto.builder()
                .name("배달기사")
                .phone("000-0000-0000")
                .build();

        // when
        Rider rider = riderService.createRider(createRiderRequestDto);

        // then
        assertNotNull(rider.getId());
    }

    @Test
    public void 배달기사찾기() throws Exception{
        // given

        Rider rider1 = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        Rider rider2 = Rider.builder()
                .name("배달기사2")
                .phone("000-0000-1111")
                .status(true)
                .build();

        riderRepository.save(rider1);
        riderRepository.save(rider2);

        // when
        List<RiderDto> ridersDto = riderService.findRiderAll();

        // then
        assertEquals(2, ridersDto.size());
    }

    @Test
    public void 배달기사변경() throws Exception{
        // given
        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        Rider savedRider = riderRepository.save(rider);

        UpdateRiderRequestDto updateRiderRequestDto = UpdateRiderRequestDto.builder()
                .name("배달기사2")
                .phone("000-1111-1111")
                .status(false)
                .build();

        // when
        riderService.updateRider(savedRider.getId(), updateRiderRequestDto);

        // then
        Optional<Rider> findRider = riderRepository.findById(savedRider.getId());
        Rider riderTest = findRider.orElseThrow(IllegalArgumentException::new);

        assertEquals(Objects.requireNonNullElse(updateRiderRequestDto.getName(), rider.getName()), riderTest.getName());
        assertEquals(Objects.requireNonNullElse(updateRiderRequestDto.getPhone(), rider.getPhone()), riderTest.getPhone());
    }

    @Test
    public void 라이더삭제() throws Exception{
        // given
        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        Rider savedRider = riderRepository.save(rider);

        // when
        riderService.deleteRiderById(savedRider.getId());

        // then
        Optional<Rider> deletedRiderOptional = riderRepository.findById(savedRider.getId());
        assertFalse(deletedRiderOptional.isPresent());
    }

}
