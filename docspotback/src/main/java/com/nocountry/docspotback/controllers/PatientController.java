package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.PatientDTO;
import com.nocountry.docspotback.dto.ReservationDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.services.impl.PatientServiceImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientServiceImpl service; // Inyección de la instancia del servicio de pacientes

    @Autowired
    private ModelMapper mapper; // Inyección de la instancia del mapeador de modelos

    /**
     * Método para obtener la lista de todos los pacientes.
     *
     * @return ResponseEntity con la lista de pacientes en formato DTO
     */
    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> findAll(){
        List<PatientDTO> list = service.findAll().stream().map(p->mapper.map(p,PatientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Método para obtener un paciente por su ID.
     *
     * @param id Identificador único del paciente
     * @return ResponseEntity con el paciente en formato DTO si existe, sino lanza una excepción
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable("id")UUID id){
        Patient obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,PatientDTO.class),HttpStatus.OK);
        }
    }

    /**
     * Método para crear un nuevo paciente.
     *
     * @param dto Datos del paciente en formato DTO
     * @return ResponseEntity con la ubicación del nuevo paciente creado
     */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PatientDTO dto){
        Patient obj = service.save(mapper.map(dto, Patient.class));
        //localhost:8000/patients/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Método para actualizar un paciente existente.
     *
     * @param dto Datos del paciente en formato DTO
     * @return ResponseEntity con el paciente actualizado
     */
    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody PatientDTO dto){
        Patient obj = service.update(mapper.map(dto, Patient.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    /**
     * Método para eliminar un paciente por su ID.
     *
     * @param id Identificador único del paciente
     * @return ResponseEntity con estado NO_CONTENT si se elimina correctamente
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Patient obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Método para obtener un paciente por su ID con enlaces HATEOAS.
     *
     * @param id Identificador único del paciente
     * @return EntityModel con el paciente en formato DTO y enlaces HATEOAS
     */
    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") UUID id) {
        Patient obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        EntityModel<PatientDTO> resource = EntityModel.of(mapper.map(obj, PatientDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("patient-info1"));
        resource.add(link2.withRel("patient-info2"));
        return resource;
    }

    /**
     * Método para obtener la lista de reservas de un paciente por su ID.
     *
     * @param id Identificador único del paciente
     * @return ResponseEntity con la lista de reservas en formato DTO
     */
    @GetMapping("/all-reservations/{id}")
    public ResponseEntity<List<ReservationDTO>> findAllReservation(@PathVariable("id")UUID id){
        List<Reservation> list = service.getAllReservationByPatientId(id);
        List<ReservationDTO>listDto=mapper.map(list,new TypeToken<List<ReservationDTO>>(){}.getType());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}