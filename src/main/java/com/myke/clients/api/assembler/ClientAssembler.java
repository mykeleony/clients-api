package com.myke.clients.api.assembler;

import com.myke.clients.api.model.input.ClientInput;
import com.myke.clients.domain.model.Client;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientAssembler {

    private ModelMapper modelMapper;

    public Client toEntity(ClientInput clientInput) {
        return modelMapper.map(clientInput, Client.class);
    }

}
