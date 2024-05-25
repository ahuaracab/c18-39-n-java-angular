package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.ProfessionalDTO;
import com.nocountry.docspotback.dto.ProfessionalListSpecialtyDTO;
import com.nocountry.docspotback.dto.ReservationDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.models.Reservation;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
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
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {

    private static final Logger log = LoggerFactory.getLogger(ProfessionalController.class);

    @Autowired
    private ProfessionalServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ProfessionalDTO>> findAll(){
        List<ProfessionalDTO> list = service.findAll().stream().map(p->mapper.map(p,ProfessionalDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO>findById(@PathVariable("id") UUID id){
        Professional obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,ProfessionalDTO.class),HttpStatus.OK);
        }
    }

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

    @PutMapping
    public ResponseEntity<Professional> update(@RequestBody ProfessionalDTO dto){
        Professional obj = service.update(mapper.map(dto, Professional.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Professional obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


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

    @GetMapping("/professional-order")
    public ResponseEntity<List<ProfessionalDTO>> getOrganizedProfessionals(
            @RequestParam("page") @Min(0) int page,
            @RequestParam("size") @Min(1) int size,
            @RequestParam("sortDir") @Pattern(regexp = "ASC|DESC") String sortDir,
            @RequestParam("sort") String sort) {

        try {
            List<Professional> list = service.getAllOrder(page, size, sortDir, sort);
            List<ProfessionalDTO> listDto = mapper.map(list,new TypeToken<List<ProfessionalDTO>>(){}.getType());
            return new ResponseEntity<>(listDto, HttpStatus.OK);
        } catch (Exception e) {
            // Log the error and return a suitable error response
            log.error("Error getting organized professionals", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
