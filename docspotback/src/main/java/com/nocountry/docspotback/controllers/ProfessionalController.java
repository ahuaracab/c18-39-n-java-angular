package com.nocountry.docspotback.controllers;

import com.nocountry.docspotback.dto.ProfessionalDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Professional;
import com.nocountry.docspotback.services.impl.ProfessionalServiceImpl;
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
@RequestMapping("/api/professional")
public class ProfessionalController {
    @Autowired
    private ProfessionalServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/admin")
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
    public ResponseEntity<Void> save(@RequestBody ProfessionalDTO dto){
        Professional obj = service.save(mapper.map(dto, Professional.class));
        //localhost:8000/professionals/5
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
}
