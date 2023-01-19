package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> list() {
        return clientRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Client> searchName(@PathVariable String name) {
        return clientRepository.findByNameContaining(name);
    }
}
