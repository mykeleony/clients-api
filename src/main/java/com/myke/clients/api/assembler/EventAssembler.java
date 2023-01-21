package com.myke.clients.api.assembler;

import com.myke.clients.api.model.output.EventOutput;
import com.myke.clients.domain.model.Event;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EventAssembler {

    private ModelMapper modelMapper;

    public EventOutput toModel(Event event) {
        return modelMapper.map(event, EventOutput.class);
    }

    public List<EventOutput> toCollectionModel(List<Event> events) {
        return events.stream()
                .map(this :: toModel)
                .collect(Collectors.toList());
    }
}