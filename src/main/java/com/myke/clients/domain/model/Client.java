package com.myke.clients.domain.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Client {

    private Long id;
    private String name;
    private String email;
    private String cellNum;
}
