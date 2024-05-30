package com.nocountry.docspotback.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api/health")
public class Health {

    @GetMapping
    public ResponseEntity<Map<String, Date>> list() {
        Map<String, Date> resp = new HashMap<>();
        resp.put("timestamp", new Date());
        return ResponseEntity.ok(resp);
    }
}
