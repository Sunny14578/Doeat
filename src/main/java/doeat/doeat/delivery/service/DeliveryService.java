package doeat.doeat.delivery.service;

import doeat.doeat.delivery.domain.Delivery;
import doeat.doeat.delivery.dto.DeliveryDto;
import doeat.doeat.delivery.dto.request.CreateDeliveryRequestDto;
import doeat.doeat.delivery.dto.request.UpdateDeliveryRequestDto;
import doeat.doeat.delivery.repository.DeliveryRepository;
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
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery createDelivery(CreateDeliveryRequestDto requestDto) {
        Delivery delivery = Delivery.builder()
                .rider(requestDto.getRider())
                .order(requestDto.getOrder())
                .name(requestDto.getName())
                .build();

        return deliveryRepository.save(delivery);
    }

    @Transactional
    public List<DeliveryDto> findDeliveryAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        return mapToDtoList(deliveries);
    }

    private List<DeliveryDto> mapToDtoList(List<Delivery> deliveries) {
        List<DeliveryDto> deliverysDto = new ArrayList<>();

        for (Delivery delivery : deliveries){
            DeliveryDto deliveryDto = DeliveryDto.builder()
                    .id(delivery.getId())
                    .name(delivery.getName())
                    .order(delivery.getOrder())
                    .rider(delivery.getRider())
                    .status(delivery.isStatus())
                    .build();

            deliverysDto.add(deliveryDto);
        }

        return deliverysDto;
    }

    @Transactional
    public DeliveryDto updateDelivery(Long id, UpdateDeliveryRequestDto requestDto) {
        Optional<Delivery> findDelivery = deliveryRepository.findById(id);
        Delivery delivery = findDelivery.orElseThrow(IllegalArgumentException::new);

        delivery.update(
                requestDto.getRider(),
                requestDto.getName(),
                requestDto.isStatus()
        );

        DeliveryDto deliveryDto = DeliveryDto.builder()
                .id(delivery.getId())
                .order(delivery.getOrder())
                .rider(delivery.getRider())
                .name(delivery.getName())
                .status(delivery.isStatus())
                .build();

        return deliveryDto;
    }

    @Transactional
    public void deleteDeliveryById(Long id) {
        deliveryRepository.deleteById(id);
    }


}
