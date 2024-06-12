package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.SpecialtyDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.services.impl.SpecialtyServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "API de Especialidades Médicas", description = "Se puede crear,listar todo, actualizar y/o elimanar especialidades Médicass")
@RestController
@RequestMapping("/api/specialty")
@CrossOrigin("*")
public class SpecialtyController {
    @Autowired
    private SpecialtyServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @Operation(
    	      summary = "Lista todas las especialidades",
    	      description = "Lista todas las especialidades el acceso es publico",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping()
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO> list = service.findAll().stream().map(p->mapper.map(p,SpecialtyDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(
  	      summary = "Busca una especialidad Médicas por id ",
  	      description = "Devuelve solo una especialidad Médicas tomando como parámetro el id de la especialidad",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO>findById(@PathVariable("id") UUID id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,SpecialtyDTO.class),HttpStatus.OK);
        }
    }

    @Operation(
    	      summary = "Guarda una especialidad médicas",
    	      description = "Guard solo una especialidad Médicas en la base de datos",
    	      tags = { })
	  @ApiResponses({
  	      @ApiResponse(responseCode = "201",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(mapper.map(dto, Specialty.class));
        //localhost:8000/specialtys/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
    	      summary = "Actualiza una especialidad médicas por id ",
    	      description = "Actualiza solo una especialidad Médicas tomando como parámetro el id de la especialidad y un json de los datos a cambiar",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping
    public ResponseEntity<Specialty> update(@RequestBody SpecialtyDTO dto){
        Specialty obj = service.update(mapper.map(dto, Specialty.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @Operation(
    	      summary = "Elimina una especialidad médicas por id ",
    	      description = "Elimina solo una especialidad médicas tomando como parámetro el id de la especialidad",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
  	      summary = "Lista 2 rutas http para encontrar una especialidad médicas",
  	      description = "Se necesita pasar el id de la especialid Médicas",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =SpecialtyDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/hateoas/{id}")
    public EntityModel<SpecialtyDTO> findByIdHateoas(@PathVariable("id") UUID id) {
        Specialty obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        EntityModel<SpecialtyDTO> resource = EntityModel.of(mapper.map(obj, SpecialtyDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("specialty-info1"));
        resource.add(link2.withRel("specialty-info2"));
        return resource;
    }
}
