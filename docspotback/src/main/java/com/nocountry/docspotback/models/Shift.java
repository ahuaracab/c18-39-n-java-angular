package com.nocountry.docspotback.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shifts")
public class Shift extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_shift")
    private UUID idShift=UUID.randomUUID();

    @Column(name = "date_shift",nullable = false)
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dateShift;

    @Column(name = "state_shift",nullable = false)
    private Boolean sateShift;

    @Column(name = "repeat_shift",nullable = false)
    private Boolean repeatShift;

    @ManyToOne
    private Professional professional;

    @OneToOne
    private Reservation reservation;


}
