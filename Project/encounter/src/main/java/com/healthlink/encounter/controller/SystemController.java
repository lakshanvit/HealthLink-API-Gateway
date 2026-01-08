package com.healthlink.encounter.controller;

import com.healthlink.encounter.dto.EncounterContactInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SystemController {

    @Autowired
    private EncounterContactInfoDto encounterContactInfoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<EncounterContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(encounterContactInfoDto);
    }
}
