package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.service.DeliveryRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private DeliveryRequestService deliveryRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@RequestBody Delivery delivery) {
        return deliveryRequestService.request(delivery);
    }


}
