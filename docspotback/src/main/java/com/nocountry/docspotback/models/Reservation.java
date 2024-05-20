package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reservation")
    private UUID idReservation=UUID.randomUUID();

    @Column(name = "query_intent",nullable = false)
    private String queryIntent;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Shift shift;
}
