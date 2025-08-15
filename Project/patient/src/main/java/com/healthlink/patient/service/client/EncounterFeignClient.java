package com.healthlink.patient.service.client;

import com.healthlink.patient.dto.EncounterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("encounter")
public interface EncounterFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<EncounterDto> fetchEncounterDetails(@RequestParam String patientNo);

}
