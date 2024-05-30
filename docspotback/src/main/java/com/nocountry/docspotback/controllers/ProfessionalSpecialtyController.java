package com.nocountry.docspotback.controllers;


import com.nocountry.docspotback.dto.ProfessionalSpecialtyDTO;

import com.nocountry.docspotback.models.ProfessionalSpecialty;
import com.nocountry.docspotback.services.IProfessionalSpecialtyService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.websocket.server.PathParam;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/professional-specialties")
public class ProfessionalSpecialtyController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProfessionalSpecialtyService service;

    @GetMapping("/professional/{idProfessional}")
    public ResponseEntity<List<ProfessionalSpecialtyDTO>> getProfessionalById(@PathVariable("idProfessional") UUID idProfessional) {
        List<ProfessionalSpecialty> lst = service.getSpecialtyByProfessionalId(idProfessional);
        List<ProfessionalSpecialtyDTO> lstDTO = mapper.map(lst, new TypeToken<List<ProfessionalSpecialtyDTO>>() {
        }.getType());
        return new ResponseEntity<>(lstDTO, HttpStatus.OK);
    }


}

