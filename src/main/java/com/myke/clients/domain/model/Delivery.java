package com.myke.clients.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "d_status")
    private DeliveryStatus status;

    private BigDecimal rate;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "finalization_date")
    private LocalDateTime finalizationDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Delivery delivery)) return false;
        return getId().equals(delivery.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
