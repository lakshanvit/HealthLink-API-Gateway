package com.healthlink.patient.service.impl;

import com.healthlink.patient.dto.EncounterDto;
import com.healthlink.patient.dto.ObservationDto;
import com.healthlink.patient.dto.PatientDashBoardDto;
import com.healthlink.patient.entity.Patient;
import com.healthlink.patient.exception.ResourceNotFoundException;
import com.healthlink.patient.mapper.PatientFhirMapper;
import com.healthlink.patient.mapper.PatientMapper;
import com.healthlink.patient.repository.PatientRepository;
import com.healthlink.patient.service.IPatientService;
import com.healthlink.patient.service.client.EncounterFeignClient;
import com.healthlink.patient.service.client.ObservationFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements IPatientService {

    private PatientFhirMapper patientFhirMapper;

    private PatientRepository patientRepository;
    private EncounterFeignClient encounterFeignClient;
    private ObservationFeignClient observationFeignClient;

    @Override
    public PatientDashBoardDto fetchDashBoardDetails(String patientNo) {

        Patient patient = patientRepository.findByPatientNo(patientNo).orElseThrow(
                () -> new ResourceNotFoundException("Patient", "patientNo", patientNo)
        );

        PatientDashBoardDto patientDashBoardDto = PatientMapper.mapToPatientDashBoardDto(patient, new PatientDashBoardDto());

        ResponseEntity<EncounterDto> encounterDtoResponseEntity = encounterFeignClient.fetchEncounterDetails(patientNo);
        patientDashBoardDto.setEncounterDto(encounterDtoResponseEntity.getBody());

        ResponseEntity<ObservationDto> observationDtoResponseEntity = observationFeignClient.fetchObservationDetails(patientNo);
        patientDashBoardDto.setObservationDto(observationDtoResponseEntity.getBody());

        return patientDashBoardDto;
    }

    @Override
    public Patient createPatient(org.hl7.fhir.r4.model.Patient fhirPatient) {
        Patient patient = patientFhirMapper.toPatientEntity(fhirPatient);
        patient.setPublicId(generateUuid());
        patient.setPatientNo(generateMrn());
        return patientRepository.save(patient);
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
}
