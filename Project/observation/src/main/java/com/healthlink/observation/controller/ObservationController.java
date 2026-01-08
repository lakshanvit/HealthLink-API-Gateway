package com.healthlink.observation.controller;

import com.healthlink.observation.dto.ObservationDto;
import com.healthlink.observation.service.IObservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ObservationController {

    private static final Logger logger = LoggerFactory.getLogger(ObservationController.class);

    private IObservationService iObservationService;

    public ObservationController(IObservationService iObservationService) {
        this.iObservationService = iObservationService;
    }

    @GetMapping("/fetch")
    public ResponseEntity<ObservationDto> fetchObservationDetails(@RequestHeader("healthlink-correlation-id") String correlationId,
                                                                  @RequestParam String patientNo) {
        logger.debug("healthLink-correlation-id found: {}", correlationId);
        ObservationDto observationDto = iObservationService.fetchObservation(patientNo);
        return ResponseEntity.status(HttpStatus.OK).body(observationDto);
    }
}
