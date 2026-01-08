package com.healthlink.encounter.controller;

import com.healthlink.encounter.dto.EncounterDto;
import com.healthlink.encounter.service.IEncounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EncounterController {

    private static final Logger logger = LoggerFactory.getLogger(EncounterController.class);

    private IEncounterService iEncounterService;

    public EncounterController (IEncounterService iEncounterService){
        this.iEncounterService = iEncounterService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<EncounterDto> fetchLastVisitDetails(@RequestHeader("healthlink-correlation-id") String correlationId,
                                                              @RequestParam String patientNo){
        logger.debug("healthLink-correlation-id found: {}", correlationId);
        EncounterDto encounterDto = iEncounterService.fetchLastVisit(patientNo);
        return ResponseEntity.status(HttpStatus.OK).body(encounterDto);
    }

}
