package com.myke.clients.api.assembler;

import com.myke.clients.api.model.input.DeliveryInput;
import com.myke.clients.api.model.output.DeliveryOutput;
import com.myke.clients.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DeliveryAssembler {

    private ModelMapper modelMapper;

    public DeliveryOutput toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryOutput.class);
    }

    public List<DeliveryOutput> toCollectionModel(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(this :: toModel)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryInput deliveryInput) {
        return modelMapper.map(deliveryInput, Delivery.class);
    }
}
