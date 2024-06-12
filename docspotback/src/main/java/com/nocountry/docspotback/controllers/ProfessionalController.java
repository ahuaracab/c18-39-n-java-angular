package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.ProfessionalDTO;
import com.nocountry.docspotback.dto.ProfessionalDTO;
import com.nocountry.docspotback.dto.ProfessionalListSpecialtyDTO;
import com.nocountry.docspotback.dto.ReservationResponseDto;
import com.nocountry.docspotback.dto.ShiftDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Professional;

import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;

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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Tag(name = "API de Profesionales de la Salud", description = "Se puede crear,listar todo, actualizar y/o eliminar Profesionales de la Salud")
@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {

    @Autowired
    private ProfessionalServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @Operation(
  	      summary = "Lista todos los Profesionales",
  	      description = "Lista todos los rofesionales inscritos en la aplicación",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    public ResponseEntity<List<ProfessionalDTO>> findAll(){
        List<ProfessionalDTO> list = service.findAll().stream().map(p->mapper.map(p,ProfessionalDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @Operation(
    	      summary = "Busca un Profesional por ID",
    	      description = "Busca un Profesional.Se requiere el parametro ID del rofesional",
    	      tags = { })
	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO>findById(@PathVariable("id") UUID id){
        Professional obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,ProfessionalDTO.class),HttpStatus.OK);
        }
    }

    @Operation(
  	      summary = "Crea un rofesional",
  	      description = "Crea un Profesional.Se requiere enviar los parametros descritos a continuación",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ProfessionalListSpecialtyDTO dto) {
        Professional professional = mapper.map(dto.getConsult(), Professional.class);
        List<Specialty> specialties = mapper.map(dto.getListSpecialty(), new TypeToken<List<Specialty>>() {
        }.getType());

        Professional obj = service.saveTransactional(professional, specialties);
        //localhost:8080/consults/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdProfessional()).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
  	      summary = "Actualiza datos de un Profesional por ID",
  	      description = "Actualiza los datos de un Profesional.Se envia los atributos a actualizar del rofesional y el ID del Profesional",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping
    public ResponseEntity<Professional> update(@RequestBody ProfessionalDTO dto){
        Professional obj = service.update(mapper.map(dto, Professional.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @Operation(
  	      summary = "Elimina un Profesional por ID",
  	      description = "Elimina un Profesional.Se requiere el parametro ID del rofesional",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Professional obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(
  	      summary = "Lista dos rutas de un Profesional por ID",
  	      description = "Lista dos rutas de un  Profesional.Se requiere el parametro ID del rofesional",
  	      tags = { })
  	  @ApiResponses({
  	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
  	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
  	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/hateoas/{id}")
    public EntityModel<ProfessionalDTO> findByIdHateoas(@PathVariable("id") UUID id) {
        Professional obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        EntityModel<ProfessionalDTO> resource = EntityModel.of(mapper.map(obj, ProfessionalDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("professional-info1"));
        resource.add(link2.withRel("professional-info2"));
        return resource;
    }

    @Operation(
    	      summary = "Paginación de profesionales por especialidad",
    	      description = "Paginación de profesionales por nombre de especialidad.Además se puede ordenar por cualquier atributo del profesional()",
    	      tags = { })
    	  @ApiResponses({
    	      @ApiResponse(responseCode = "200",content= {@Content(schema = @Schema(implementation =ProfessionalDTO.class),mediaType = "application/json")}),
    	      @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
    	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/pageable/{bySpecialtyName}")
    public ResponseEntity<List<ProfessionalDTO>> getAllByNameSpecialty(
            @PathVariable("bySpecialtyName")@Parameter(example = "Cardiología") String nameProfessional,
            @RequestParam("numberPage") @Parameter(example = "0") Integer numberPage,
            @RequestParam("pageSize")@Parameter(example = "10") Integer sizePage,
            @RequestParam("orderBy") @Parameter(example = "value_query") String orderBy,
            @RequestParam("sort") @Parameter(example = "ASC") String sort) {
        try {
            List<Professional> lst = service.getAllProfessionalsBySpecialityName(nameProfessional, numberPage, sizePage, orderBy, sort);
            List<ProfessionalDTO> listDto = new ArrayList<>();
            for (Professional ps : lst) {
                listDto.add(mapper.map(ps, ProfessionalDTO.class));
            }
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}
