package com.myke.clients.domain.service;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.DeliveryStatus;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryRequestService {
    private ClientServiceCatalog clientServiceCatalog;
    private DeliveryRepository deliveryRepository;

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public ResponseEntity<Delivery> search(Long id) {
        return deliveryRepository.findById(id)
                .map(ResponseEntity :: ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = clientServiceCatalog.search(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDING);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }

}
