package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.service.DeliveryRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private DeliveryRequestService deliveryRequestService;

    @GetMapping
    public List<Delivery> findAll() {
        return deliveryRequestService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> searchId(@PathVariable Long id) {
        return deliveryRequestService.search(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@Valid @RequestBody Delivery delivery) {
        return deliveryRequestService.request(delivery);
    }


}
