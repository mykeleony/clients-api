package com.myke.clients.api.controller;

import com.myke.clients.api.assembler.DeliveryAssembler;
import com.myke.clients.api.model.input.DeliveryInput;
import com.myke.clients.api.model.output.DeliveryOutput;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.repository.DeliveryRepository;
import com.myke.clients.domain.service.DeliveryFinalizationService;
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

    private DeliveryRepository deliveryRepository;
    private DeliveryRequestService deliveryRequestService;
    private DeliveryAssembler deliveryAssembler;
    private DeliveryFinalizationService deliveryFinalizationService;

    @GetMapping
    public List<DeliveryOutput> findAll() {
        List<Delivery> deliveries = deliveryRequestService.findAll();

        return deliveryAssembler.toCollectionModel(deliveries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOutput> searchId(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(delivery -> ResponseEntity.ok(deliveryAssembler.toModel(delivery))).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryOutput request(@Valid @RequestBody DeliveryInput deliveryInput) {
        Delivery newDelivery = deliveryAssembler.toEntity(deliveryInput);
        Delivery requestedDelivery = deliveryRequestService.request(newDelivery);

        return deliveryAssembler.toModel(requestedDelivery);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{deliveryId}/conclusion")
    public void conclude(@PathVariable Long deliveryId) {
        deliveryFinalizationService.conclude(deliveryId);
    }

}
