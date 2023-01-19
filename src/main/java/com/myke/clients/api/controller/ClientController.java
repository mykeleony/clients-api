package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.repository.ClientRepository;
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
             /*
             Optional<Client> client = clientRepository.findById(id);

             if (client.isPresent())
                 return ResponseEntity.ok(client.get());

             return ResponseEntity.notFound().build();
             */
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client add(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @Valid @RequestBody Client client) {
        if(!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        client.setId(id);
        client = clientRepository.save(client);

        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        if(!clientRepository.existsById(id))
            return ResponseEntity.notFound().build();

        clientRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
