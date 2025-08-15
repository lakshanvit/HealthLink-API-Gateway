package com.healthlink.encounter.controller;

import com.healthlink.encounter.dto.EncounterDto;
import com.healthlink.encounter.service.IEncounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EncounterController {

    private IEncounterService iEncounterService;

    public EncounterController (IEncounterService iEncounterService){
        this.iEncounterService = iEncounterService;
    }

    @GetMapping("/fetchLastVisit")
    public ResponseEntity<EncounterDto> fetchLastVisitDetails(@RequestParam String patientNo){
        EncounterDto encounterDto = iEncounterService.fetchLastVisit(patientNo);
        return ResponseEntity.status(HttpStatus.OK).body(encounterDto);
    }

}
