package com.myke.clients.domain.service;

import com.myke.clients.domain.model.Client;
import com.myke.clients.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ClientServiceCatalog {
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public List<Client> searchName (String name) {
        return clientRepository.findByNameContaining(name);
    }

    public ResponseEntity<Client> findById(Long id) {
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

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Transactional
    public void remove(Long id) {
        clientRepository.deleteById(id);
    }


}
