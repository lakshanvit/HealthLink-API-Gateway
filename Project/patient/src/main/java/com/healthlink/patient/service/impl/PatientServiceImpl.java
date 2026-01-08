package com.healthlink.patient.service.impl;

import com.healthlink.patient.dto.EncounterDto;
import com.healthlink.patient.dto.ObservationDto;
import com.healthlink.patient.dto.PatientDashBoardDto;
import com.healthlink.patient.dto.PatientMsgDto;
import com.healthlink.patient.entity.Patient;
import com.healthlink.patient.exception.ResourceNotFoundException;
import com.healthlink.patient.mapper.PatientFhirMapper;
import com.healthlink.patient.mapper.PatientMapper;
import com.healthlink.patient.repository.PatientRepository;
import com.healthlink.patient.service.IPatientService;
import com.healthlink.patient.service.client.EncounterFeignClient;
import com.healthlink.patient.service.client.ObservationFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    private PatientFhirMapper patientFhirMapper;

    private PatientRepository patientRepository;
    private EncounterFeignClient encounterFeignClient;
    private ObservationFeignClient observationFeignClient;
    private final StreamBridge streamBridge;

    @Override
    public PatientDashBoardDto fetchDashBoardDetails(String patientNo, String correlationId) {

        Patient patient = patientRepository.findByPatientNo(patientNo).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "patientNo", patientNo)
        );

        PatientDashBoardDto patientDashBoardDto = PatientMapper.mapToPatientDashBoardDto(patient, new PatientDashBoardDto());

        ResponseEntity<EncounterDto> encounterDtoResponseEntity = encounterFeignClient.fetchEncounterDetails(correlationId, patientNo);
        if(null != encounterDtoResponseEntity){
            patientDashBoardDto.setEncounterDto(encounterDtoResponseEntity.getBody());
        }


        ResponseEntity<ObservationDto> observationDtoResponseEntity = observationFeignClient.fetchObservationDetails(correlationId, patientNo);
        if(null != observationDtoResponseEntity) {
            patientDashBoardDto.setObservationDto(observationDtoResponseEntity.getBody());
        }

        return patientDashBoardDto;
    }

    @Override
    public Patient createPatient(org.hl7.fhir.r4.model.Patient fhirPatient) {
        Patient patient = patientFhirMapper.toPatientEntity(fhirPatient);
        patient.setPublicId(generateUuid());
        patient.setPatientNo(generateMrn());
        Patient savedPatient = patientRepository.save(patient);
        sendCommunication(savedPatient);
        return savedPatient;
    }

    private void sendCommunication(Patient patient) {
        var patientMsgDto = new PatientMsgDto(patient.getPatientId(), patient.getFirstName(),
                patient.getEmail(), patient.getMobileNo());
        log.info("Sending Communication request for the details: {}", patientMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", patientMsgDto);
        log.info("Is the Communication request successfully triggered ? : {}", result);
    }

    private String generateUuid() {
        return UUID.randomUUID().toString();
    }

    private String generateMrn() {
        return "MRN-" + Year.now().getValue() + "-" + String.format("%05d", patientRepository.count() + 1);
    }

    @Override
    public org.hl7.fhir.r4.model.Patient getFhirPatient(String publicId) {
        Patient patient = patientRepository.findByPublicId(publicId).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "publicId", publicId)
        );
        return patientFhirMapper.toFhirResource(patient);
    }

    @Override
    public boolean updateCommunicationStatus(Long patientId) {
        boolean isUpdated = false;
        if(patientId != null) {
            Patient patient = patientRepository.findById(patientId).orElseThrow(
                    () -> new ResourceNotFoundException("Patient", "patientId", patientId.toString())
            );
            patient.setCommunicationSw(true);
            patientRepository.save(patient);
            isUpdated = true;
        }
        return isUpdated;
    }
}
