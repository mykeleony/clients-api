package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("search/{name}")
    public List<Client> searchName(@PathVariable String name) {
        return clientRepository.findByNameContaining(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> searchId(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        //     Optional<Client> client = clientRepository.findById(id);
        //
        //     if (client.isPresent())
        //         return ResponseEntity.ok(client.get());
        //
        //     return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@RequestBody Client client) {
        return clientRepository.save(client);
    }
}
