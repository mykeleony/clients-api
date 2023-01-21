package com.myke.clients.api.assembler;

import com.myke.clients.api.model.output.EventOutput;
import com.myke.clients.domain.model.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EventAssembler {

    private ModelMapper modelMapper;

    public EventOutput toModel(Event event) {
        return modelMapper.map(event, EventOutput.class);
    }
}
