package com.healthlink.patient.service.client;

import com.healthlink.patient.dto.ObservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("observation")
public interface ObservationFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<ObservationDto> fetchObservationDetails(@RequestParam String patientNo);

}
