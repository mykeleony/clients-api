package com.myke.clients.domain.service;

import com.myke.clients.domain.exception.BusinessException;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.Event;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class EventRegistrationService {

    private DeliveryRepository deliveryRepository;
    private SearchDeliveryService searchDeliveryService;

    @Transactional
    public Event register(Long deliveryId, String description) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return delivery.addEvent(description);
    }

}
