package com.healthlink.observation.controller;

import com.healthlink.observation.dto.ObservationContactInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SystemController {

    @Autowired
    private ObservationContactInfoDto observationContactInfoDto;

    @GetMapping("/contact-info")
    public ResponseEntity<ObservationContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(observationContactInfoDto);
    }
}
