package com.myke.clients.domain.model;

import com.myke.clients.domain.exception.BusinessException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Client client;

    @Embedded
    private Recipient recipient;

    @Enumerated(EnumType.STRING)
    @Column(name = "d_status")
    private DeliveryStatus status;

    @NotNull
    private BigDecimal rate;

    @Column(name = "order_date")
    private OffsetDateTime orderDate;

    @Column(name = "finalization_date")
    private OffsetDateTime finalizationDate;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Event> events = new ArrayList<>();

    public Event addEvent(String description) {
        Event event = new Event(this, description, OffsetDateTime.now());

        this.getEvents().add(event);

        return event;
    }

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

    public void conclude() {
        if (!canBeConcluded())
            throw new BusinessException("Delivery could not be concluded.");

        setStatus(DeliveryStatus.CONCLUDED);
        setFinalizationDate(OffsetDateTime.now());
    }

    public boolean canBeConcluded() {
        return getStatus().equals(DeliveryStatus.PENDING);
    }
}
