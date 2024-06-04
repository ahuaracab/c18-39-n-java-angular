package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.PatientDTO;
import com.nocountry.docspotback.dto.ReservationDTO;
import com.nocountry.docspotback.dto.SpecialtyDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Patient;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.services.impl.PatientServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "API de Pacientes", description = "Se puede crear,listar todo, actualizar y/o eliminar Pacientes")
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @Operation(
    	      summary = "Lista todos los Pacientes",
    	      description = "Lista todos los pacientes inscritos en la aplicación",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    public ResponseEntity<List<PatientDTO>> findAll(){
        List<PatientDTO> list = service.findAll().stream().map(p->mapper.map(p,PatientDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(
  	      summary = "Busca un Paciente por ID",
  	      description = "Busca un Paciente.Se requiere el parametro ID del paciente",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO>findById(@PathVariable("id") @Parameter(example = "") UUID id){
        Patient obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,PatientDTO.class),HttpStatus.OK);
        }
    }
    @Operation(
    	      summary = "Crea un paciente",
    	      description = "Crea un Paciente.Se requiere enviar los parametros descritos a continuación",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody PatientDTO dto){
        Patient obj = service.save(mapper.map(dto, Patient.class));
        //localhost:8000/patients/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdPatient()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
    	      summary = "Actualiza datos de un Paciente por ID",
    	      description = "Actualiza los datos de un Paciente.Se envia los atributos a actualizar del paciente y el ID del Paciente",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody PatientDTO dto){
        Patient obj = service.update(mapper.map(dto, Patient.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @Operation(
    	      summary = "Elimina un Paciente por ID",
    	      description = "Elimina un Paciente.Se requiere el parametro ID del paciente",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Parameter(example = "") UUID id){
        Patient obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
    	      summary = "Lista dos rutas de un Paciente por ID",
    	      description = "Lista dos rutas de un  Paciente.Se requiere el parametro ID del paciente",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/hateoas/{id}")
    public EntityModel<PatientDTO> findByIdHateoas(@PathVariable("id") @Parameter(example = "") UUID id) {
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

    @Operation(
    	      summary = "Lista todas las reservaciones por ID",
    	      description = "Busca un Paciente.Se requiere el parametro ID del paciente",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =PatientDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/all-reservations/byPatient/{id}")
    public ResponseEntity<List<ReservationDTO>> findAllReservation(@PathVariable("id") @Parameter(example = "") UUID id){
        List<Reservation> list = service.getAllReservationByPatientId(id);
        List<ReservationDTO>listDto=mapper.map(list,new TypeToken<List<ReservationDTO>>(){}.getType());
        return new ResponseEntity<>(listDto, HttpStatus.OK);
    }
}
