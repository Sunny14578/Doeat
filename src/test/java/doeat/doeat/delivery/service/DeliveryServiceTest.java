package doeat.doeat.delivery.service;

import doeat.doeat.delivery.domain.Delivery;
import doeat.doeat.delivery.dto.DeliveryDto;
import doeat.doeat.delivery.dto.request.CreateDeliveryRequestDto;
import doeat.doeat.delivery.dto.request.UpdateDeliveryRequestDto;
import doeat.doeat.delivery.repository.DeliveryRepository;
import doeat.doeat.order.domain.Order;
import doeat.doeat.rider.domain.Rider;
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
public class DeliveryServiceTest {

    @Autowired DeliveryService deliveryService;
    @Autowired DeliveryRepository deliveryRepository;
    @Autowired RiderRepository riderRepository;

    @Test
    public void 배달등록() throws Exception{
        // given

        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        riderRepository.save(rider);

        CreateDeliveryRequestDto createDeliveryRequestDto = CreateDeliveryRequestDto.builder()
                .rider(rider)
                .name("배달1")
                .build();

        // when
        Delivery delivery = deliveryService.createDelivery(createDeliveryRequestDto);

        // then
        assertNotNull(delivery.getId());
    }

    @Test
    public void 배달찾기() throws Exception{
        // given
        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        riderRepository.save(rider);

        Delivery delivery1 = Delivery.builder()
                .rider(rider)
                .name("배달1")
                .status(true)
                .build();

        Delivery delivery2 = Delivery.builder()
                .rider(rider)
                .name("배달2")
                .status(true)
                .build();

        deliveryRepository.save(delivery1);
        deliveryRepository.save(delivery2);

        // when
        List<DeliveryDto> deliverysDto = deliveryService.findDeliveryAll();

        // then
        assertEquals(2, deliverysDto.size());
    }

    @Test
    public void 배달변경() throws Exception{
        // given
        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        riderRepository.save(rider);

        Rider rider2 = Rider.builder()
                .name("배달기사2")
                .phone("000-0000-0000")
                .status(true)
                .build();

        riderRepository.save(rider2);

        Delivery delivery = Delivery.builder()
                .rider(rider)
                .name("배달1")
                .status(true)
                .build();

        Delivery savedDelivery = deliveryRepository.save(delivery);

        UpdateDeliveryRequestDto updateDeliveryRequestDto = UpdateDeliveryRequestDto.builder()
                .name("배달2")
                .rider(rider2)
                .status(false)
                .build();

        // when
        deliveryService.updateDelivery(savedDelivery.getId(), updateDeliveryRequestDto);

        // then
        Optional<Delivery> findDelivery = deliveryRepository.findById(savedDelivery.getId());
        Delivery deliveryTest = findDelivery.orElseThrow(IllegalArgumentException::new);

        assertEquals(Objects.requireNonNullElse(updateDeliveryRequestDto.getRider().getName(), delivery.getRider().getName()),
                deliveryTest.getRider().getName());
        assertEquals(Objects.requireNonNullElse(updateDeliveryRequestDto.getName(), delivery.getName()),
                deliveryTest.getName());
        assertEquals(Objects.requireNonNullElse(updateDeliveryRequestDto.isStatus(), delivery.isStatus()),
                deliveryTest.isStatus());
    }

    @Test
    public void 배달삭제() throws Exception{
        // given
        Rider rider = Rider.builder()
                .name("배달기사1")
                .phone("000-0000-0000")
                .status(true)
                .build();

        riderRepository.save(rider);

        Delivery delivery = Delivery.builder()
                .rider(rider)
                .name("배달1")
                .status(true)
                .build();

        Delivery savedDelivery = deliveryRepository.save(delivery);

        // when
        deliveryService.deleteDeliveryById(savedDelivery.getId());

        // then
        Optional<Delivery> deletedDeliveryOptional = deliveryRepository.findById(savedDelivery.getId());
        assertFalse(deletedDeliveryOptional.isPresent());
    }


}
