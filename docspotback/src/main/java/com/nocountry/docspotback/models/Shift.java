package com.nocountry.docspotback.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
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

    @Enumerated(EnumType.STRING)
    private WeekDay day;

    @Column(name = "start_time", nullable = false, length = 5)
    private String startTime;

    @Column(name = "end_time", nullable = false, length = 5)
    private String endTime;

    @Column(name = "state_shift", nullable = false)
    private Boolean stateShift;

    @Column(name = "repeat_shift", nullable = false)
    private Boolean repeatShift;


    @OneToMany(mappedBy = "shift")
    private Set<Reservation> reservations;

}
