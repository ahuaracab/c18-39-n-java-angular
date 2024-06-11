package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shifts")
public class Shift extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_shift")
    private UUID idShift = UUID.randomUUID();

    @Column(name = "date_shift")
    private LocalDate dateShift;
    
    @Column(name = "hoursTime")
    private LocalDateTime hoursTime;
    
    private Boolean stateShift;
    
    private Boolean repeatShift;
    
    @OneToOne(mappedBy = "shift")
    private Reservation reservations;


}
