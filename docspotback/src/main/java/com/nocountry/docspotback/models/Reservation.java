package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reservation")
    private UUID idReservation = UUID.randomUUID();

    @Column(name = "query_intent")
    private String queryIntent;

    @JsonBackReference
    @ManyToOne
    private Patient patient;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_shift", nullable = false)
    private Shift shift;

}
