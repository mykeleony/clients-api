package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.repository.ClientRepository;
import com.myke.clients.domain.service.ClientServiceCatalog;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientRepository clientRepository;
    private ClientServiceCatalog clientServiceCatalog;

    @GetMapping
    public List<Client> list() {
        return clientServiceCatalog.findAll();
    }

    @GetMapping("search/{name}")
    public List<Client> searchName(@PathVariable String name) {
        return clientServiceCatalog.searchName(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> searchId(@PathVariable Long id) {
        return clientServiceCatalog.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@Valid @RequestBody Client client) {
        return clientServiceCatalog.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client) {
        if(!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        client.setId(id);
        client = clientServiceCatalog.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        if(!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        clientServiceCatalog.remove(id);

        return ResponseEntity.noContent().build();
    }
}
