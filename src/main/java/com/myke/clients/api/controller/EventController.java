package com.myke.clients.api.controller;

import com.myke.clients.api.assembler.EventAssembler;
import com.myke.clients.api.model.input.EventInput;
import com.myke.clients.api.model.output.EventOutput;
import com.myke.clients.domain.model.Delivery;
import com.myke.clients.domain.model.Event;
import com.myke.clients.domain.service.EventRegistrationService;
import com.myke.clients.domain.service.SearchDeliveryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("deliveries/{deliveryId}/events")
public class EventController {

    private EventRegistrationService eventRegistrationService;
    private EventAssembler eventAssembler;
    private SearchDeliveryService searchDeliveryService;

    @GetMapping
    public List<EventOutput> findAll(@PathVariable Long deliveryId) {
        Delivery delivery = searchDeliveryService.search(deliveryId);

        return eventAssembler.toCollectionModel(delivery.getEvents());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventOutput register(@PathVariable Long deliveryId,
                                @RequestBody @Valid EventInput eventInput) {
        Event event = eventRegistrationService.register(deliveryId, eventInput.getDescription());

        return eventAssembler.toModel(event);
    }

}
