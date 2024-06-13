package com.nocountry.docspotback.controllers;


import com.nocountry.docspotback.dto.ProfessionalDTO;
import com.nocountry.docspotback.dto.ProfessionalSpecialtyDTO;

import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.services.IProfessionalService;
import com.nocountry.docspotback.services.IProfessionalSpecialtyService;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/professional-specialties")
public class ProfessionalSpecialtyController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProfessionalSpecialtyService service;

    @Autowired
    private IProfessionalService professionalService;
    
    @GetMapping("/professional/{idProfessional}")
    public ResponseEntity<List<ProfessionalSpecialtyDTO>> getProfessionalById(@PathVariable("idProfessional") UUID idProfessional) {
        List<ProfessionalSpecialty> lst = service.getSpecialtyByProfessionalId(idProfessional);
        List<ProfessionalSpecialtyDTO> lstDTO = mapper.map(lst, new TypeToken<List<ProfessionalSpecialtyDTO>>() {
        }.getType());
        return new ResponseEntity<>(lstDTO, HttpStatus.OK);
    }


}

