package com.healthlink.patient.service.client;

import com.healthlink.patient.dto.ObservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "observation", fallback = ObservationFallback.class)
public interface ObservationFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<ObservationDto> fetchObservationDetails(@RequestHeader("healthlink-correlation-id") String correlationId,
                                                                  @RequestParam String patientNo);

}
