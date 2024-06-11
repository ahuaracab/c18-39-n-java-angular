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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nocountry.docspotback.dto.ShiftDTO;
import com.nocountry.docspotback.exception.ModelNotFoundException;
import com.nocountry.docspotback.models.Shift;
import com.nocountry.docspotback.services.impl.ShiftServiceImpl;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {
	
    @Autowired
    private ShiftServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/")
    public ResponseEntity<List<ShiftDTO>> findAll(){
        List<ShiftDTO> list = service.findAll().stream().map(p->mapper.map(p,ShiftDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftDTO>findById(@PathVariable("id")UUID id){
        Shift obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: "+ id);
        }else {
            return new ResponseEntity<>(mapper.map(obj,ShiftDTO.class),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ShiftDTO dto){
        Shift obj = service.save(mapper.map(dto, Shift.class));
        //localhost:8000/Shifts/5
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdShift()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Shift> update(@RequestBody ShiftDTO dto){
        Shift obj = service.update(mapper.map(dto, Shift.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        Shift obj = service.findById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/hateoas/{id}")
    public EntityModel<ShiftDTO> findByIdHateoas(@PathVariable("id") UUID id) {
        Shift obj = service.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }

        EntityModel<ShiftDTO> resource = EntityModel.of(mapper.map(obj, ShiftDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("Shift-info1"));
        resource.add(link2.withRel("Shift-info2"));
        return resource;
    }
    
    @GetMapping("/shifts-by-professional/{id}")
    public ResponseEntity<List<ShiftDTO>> listShiftsByProfessional(@PathVariable UUID id) {
        List<ShiftDTO> response = service.findAllShiftByProfessionalID(id).stream().map(p->mapper.map(p,ShiftDTO.class)).collect(Collectors.toList());;
        return new ResponseEntity<List<ShiftDTO>>(response, HttpStatus.OK);
    }

}
