package com.healthlink.patient.service.client;

import com.healthlink.patient.dto.EncounterDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EncounterFallback implements EncounterFeignClient{
    @Override
    public ResponseEntity<EncounterDto> fetchEncounterDetails(String correlationId, String patientNo) {
        return null;
    }
}
