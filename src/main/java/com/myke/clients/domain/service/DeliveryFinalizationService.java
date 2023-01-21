package com.myke.clients.domain.service;

import com.myke.clients.domain.exception.BusinessException;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.DeliveryStatus;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeliveryFinalizationService {

    private DeliveryRepository deliveryRepository;
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public void conclude(Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        delivery.conclude();

        delivery.setStatus(DeliveryStatus.CONCLUDED);
        deliveryRepository.save(delivery);
    }

}
