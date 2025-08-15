package com.healthlink.observation.controller;

import com.healthlink.observation.dto.ObservationDto;
import com.healthlink.observation.service.IObservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ObservationController {

    private IObservationService iObservationService;

    public ObservationController(IObservationService iObservationService) {
        this.iObservationService = iObservationService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<ObservationDto> fetchObservationDetails(@RequestParam String patientNo) {
        ObservationDto observationDto = iObservationService.fetchObservation(patientNo);
        return ResponseEntity.status(HttpStatus.OK).body(observationDto);
    }
}
