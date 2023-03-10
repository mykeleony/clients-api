package com.myke.clients.domain.service;

import com.myke.clients.domain.exception.BusinessException;
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

    public Client search(Long id) {
        return clientRepository.findById(id)
                .orElseThrow( () -> new BusinessException("This client does not exist."));
    }

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
        boolean emailInUse = clientRepository.findByEmail(client.getEmail())
                .stream().anyMatch(existingClient -> !existingClient.equals(client));

        if (emailInUse) {
            throw new BusinessException("There is already a user registered with this email. Please enter another email and try again.");
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void remove(Long id) {
        clientRepository.deleteById(id);
    }


}
