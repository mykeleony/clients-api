package com.myke.clients.domain.service;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.DeliveryStatus;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DeliveryRequestService {
    private ClientServiceCatalog clientServiceCatalog;
    private DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = clientServiceCatalog.search(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(LocalDateTime.now());

        return deliveryRepository.save(delivery);
    }

}
