package com.healthlink.patient.service.client;

import com.healthlink.patient.dto.ObservationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ObservationFallback implements ObservationFeignClient{
    @Override
    public ResponseEntity<ObservationDto> fetchObservationDetails(String correlationId, String patientNo) {
        return null;
    }
}
