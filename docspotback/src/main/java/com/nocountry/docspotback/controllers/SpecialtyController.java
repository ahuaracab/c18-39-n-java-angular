package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.SpecialtyDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Specialty;
import com.nocountry.docspotback.services.impl.SpecialtyServiceImpl;
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

@RestController
@RequestMapping("/api/specialty")
public class SpecialtyController {
    @Autowired
    private SpecialtyServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<SpecialtyDTO>> findAll(){
        List<SpecialtyDTO> list = service.findAll().stream().map(p->mapper.map(p,SpecialtyDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyDTO>findById(@PathVariable("id") UUID id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,SpecialtyDTO.class),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SpecialtyDTO dto){
        Specialty obj = service.save(mapper.map(dto, Specialty.class));
        //localhost:8000/specialtys/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdSpecialty()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Specialty> update(@RequestBody SpecialtyDTO dto){
        Specialty obj = service.update(mapper.map(dto, Specialty.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Specialty obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


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
