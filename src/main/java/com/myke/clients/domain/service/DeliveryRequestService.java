package com.myke.clients.domain.service;

import com.myke.clients.api.assembler.DeliveryAssembler;
import com.myke.clients.api.model.output.DeliveryOutput;
import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.DeliveryStatus;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryRequestService {

    private ClientServiceCatalog clientServiceCatalog;
    private DeliveryRepository deliveryRepository;
    private DeliveryAssembler deliveryAssembler;

    public List<Delivery> findAll() {
        return deliveryRepository.findAll();
    }

    public ResponseEntity<DeliveryOutput> search(Long id) {
        return deliveryRepository.findById(id)
                .map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery))).orElse(ResponseEntity.notFound().build());
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
