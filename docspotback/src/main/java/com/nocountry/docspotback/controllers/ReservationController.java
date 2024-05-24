package com.nocountry.docspotback.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.services.impl.ReservationServiceImpl;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    @Autowired
    private ReservationServiceImpl service;

    @GetMapping("/professional/{id}")
    public List<Reservation> listReservationsByProfessional(@PathVariable UUID id) {
        return service.findReservationsByProfessionalId(id);
    }
}
