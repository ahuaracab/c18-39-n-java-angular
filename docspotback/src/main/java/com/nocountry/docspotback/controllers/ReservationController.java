package com.nocountry.docspotback.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nocountry.docspotback.dto.ReservationDTO;
import com.nocountry.docspotback.dto.ReservationListDTO;
import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.services.impl.ReservationServiceImpl;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private ReservationServiceImpl service;
    
    @Autowired
    private ModelMapper mapper;

    @PreAuthorize("hasRole('ROLE_PROFESSIONAL')")
    @GetMapping("/professional/{id}")
    public ResponseEntity<List<ReservationListDTO>> listReservationsByProfessional(@PathVariable UUID id) {
        List<ReservationListDTO> response = service.findAllReservationListsByProfesionalId(id).stream().map(p->mapper.map(p,ReservationListDTO.class)).collect(Collectors.toList());;
        return new ResponseEntity<List<ReservationListDTO>>(response, HttpStatus.OK);
    }



    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> findAll(){
        List<ReservationDTO> list = service.findAll().stream().map(p->mapper.map(p,ReservationDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO>findById(@PathVariable("id")UUID id){
        Reservation obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,ReservationDTO.class),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ReservationDTO dto){
        Reservation obj = service.saveTransaction(mapper.map(dto, Reservation.class));
        //localhost:8000/Reservations/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdReservation()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Reservation> update(@RequestBody ReservationDTO dto){
        Reservation obj = service.update(mapper.map(dto, Reservation.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Reservation obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/hateoas/{id}")
    public EntityModel<ReservationDTO> findByIdHateoas(@PathVariable("id") UUID id) {
        Reservation obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        EntityModel<ReservationDTO> resource = EntityModel.of(mapper.map(obj, ReservationDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("Reservation-info1"));
        resource.add(link2.withRel("Reservation-info2"));
        return resource;
    }
    
    @PreAuthorize("hasRole('ROLE_PATIENT')")
    @GetMapping("/patient/{id}")
    public ResponseEntity<List<ReservationListDTO>> listReservationsByPatient(@PathVariable UUID id) {
        List<ReservationListDTO> response = service.findAllReservationListsByPatientId(id).stream().map(p->mapper.map(p,ReservationListDTO.class)).collect(Collectors.toList());;
        return new ResponseEntity<List<ReservationListDTO>>(response, HttpStatus.OK);
    }

    
}
