package com.myke.clients.domain.service;

import com.myke.clients.domain.exception.BusinessException;
import com.myke.clients.domain.exception.InexistentEntityException;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SearchDeliveryService {

    private DeliveryRepository deliveryRepository;

    public Delivery search(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new InexistentEntityException("There is not a delivery with the specified id."));
    }

}
