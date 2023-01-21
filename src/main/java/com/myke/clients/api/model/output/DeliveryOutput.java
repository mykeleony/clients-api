package com.myke.clients.api.model.output;

import com.myke.clients.domain.model.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class DeliveryOutput {

    private Long id;
    private ClientOutput client;
    private RecipientOutput recipient;
    private BigDecimal rate;
    private DeliveryStatus status;
    private OffsetDateTime orderDate;
    private OffsetDateTime finalizationDate;

}
