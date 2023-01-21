package com.myke.clients.api.controller;

import com.myke.clients.api.assembler.DeliveryAssembler;
import com.myke.clients.api.model.DeliveryOutput;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.service.DeliveryRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private DeliveryRequestService deliveryRequestService;
    private DeliveryAssembler deliveryAssembler;

    @GetMapping
    public List<DeliveryOutput> findAll() {
        List<Delivery> deliveries = deliveryRequestService.findAll();

        return deliveryAssembler.toCollectionModel(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOutput> searchId(@PathVariable Long id) {
        return deliveryRequestService.search(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOutput request(@Valid @RequestBody Delivery delivery) {
        Delivery requestedDelivery = deliveryRequestService.request(delivery);

        return deliveryAssembler.toModel(requestedDelivery);
    }


}
