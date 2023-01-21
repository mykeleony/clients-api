package com.myke.clients.api.controller;

import com.myke.clients.api.assembler.EventAssembler;
import com.myke.clients.api.model.input.EventInput;
import com.myke.clients.api.model.output.EventOutput;
import com.myke.clients.domain.model.Event;
import com.myke.clients.domain.service.EventRegistrationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("deliveries/{deliveryId}/events")
public class EventController {

    private EventRegistrationService eventRegistrationService;
    private EventAssembler eventAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventOutput register(@PathVariable Long deliveryId,
                                @RequestBody @Valid EventInput eventInput) {
        Event event = eventRegistrationService.register(deliveryId, eventInput.getDescription());

        return eventAssembler.toModel(event);
    }

}
