package com.myke.clients.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class Recipient {

    @NotBlank
    @Column(name = "recipient_name")
    private String name;

    @NotBlank
    @Column(name = "recipient_street")
    private String street;

    @NotBlank
    @Column(name = "recipient_number")
    private String number;

    @NotBlank
    @Column(name = "recipient_complement")
    private String complement;

    @NotBlank
    @Column(name = "recipient_district")
    private String district;

}
