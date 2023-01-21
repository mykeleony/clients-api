package com.myke.clients.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventInput {

    @NotBlank
    private String description;

}
