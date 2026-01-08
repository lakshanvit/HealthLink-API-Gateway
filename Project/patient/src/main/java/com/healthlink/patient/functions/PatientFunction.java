package com.healthlink.patient.functions;

import com.healthlink.patient.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class PatientFunction {

    private static final Logger log = LoggerFactory.getLogger(PatientFunction.class);

    @Bean
    public Consumer<Long> updateCommunication(IPatientService patientService) {
        return patientId -> {
            log.info("Updating Communication status for the patient Id : " +  patientId.toString());
            patientService.updateCommunicationStatus(patientId);

        };
    }
}
