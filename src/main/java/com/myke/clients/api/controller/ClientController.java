package com.myke.clients.api.controller;

import com.myke.clients.domain.model.Client;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @GetMapping
    public List<Client> list() {
        Client c1 = new Client(1L, "Myke", "myke.amorim@usp.br", "11959524009");
        Client c2 = new Client(2L, "Seilo", "exemplasdobblab", "01234567");

        return Arrays.asList(c1, c2);
    }
}
