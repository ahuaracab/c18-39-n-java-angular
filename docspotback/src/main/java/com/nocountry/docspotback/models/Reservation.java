package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
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
    @Column(name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "appointment_date", nullable = false)
    private Instant appointmentDate;

    @Column(name = "query_intent", nullable = false)
    private String queryIntent;

    @JsonBackReference
    @ManyToOne
    private Patient patient;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;
}
