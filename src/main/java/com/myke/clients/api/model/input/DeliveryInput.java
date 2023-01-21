package com.myke.clients.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DeliveryInput {

    @NotNull
    private Long clientId;

    @Valid
    @NotNull
    private RecipientInput recipient;

    @NotNull
    private BigDecimal rate;
}
