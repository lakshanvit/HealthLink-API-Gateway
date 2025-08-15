package com.healthlink.patient.controller;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.Constants;
import com.healthlink.patient.dto.PatientDashBoardDto;
import com.healthlink.patient.service.IPatientService;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/fhir/Patient", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class PatientController {

    private final IPatientService iPatientService;

    private final FhirContext fhirContext = FhirContext.forR4();

    public PatientController(IPatientService iPatientService) {
        this.iPatientService = iPatientService;
    }



    @PostMapping("register")
    public ResponseEntity<String> createPatient(@RequestBody String fhirJson) {
        Patient fhirPatient = (Patient) fhirContext.newJsonParser().parseResource(fhirJson);
        com.healthlink.patient.entity.Patient savedPatient = iPatientService.createPatient(fhirPatient);
        Patient responsePatient = iPatientService.getFhirPatient(savedPatient.getPublicId());

        return ResponseEntity.ok(fhirContext.newJsonParser().encodeResourceToString(responsePatient));
    }

    @GetMapping("/{publicId}")
    public ResponseEntity<String> getPatient(@PathVariable String publicId) {
        Patient fhirPatient = iPatientService.getFhirPatient(publicId);
        String fhirJson = fhirContext.newJsonParser().encodeResourceToString(fhirPatient);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(Constants.CT_FHIR_JSON))
                .body(fhirJson);
    }

    @GetMapping("/fetchPatientDashBoard")
    public ResponseEntity<PatientDashBoardDto> fetchPatientDashBoard(@RequestParam String patientNo){
        PatientDashBoardDto patientDashBoardDto = iPatientService.fetchDashBoardDetails(patientNo);
        return ResponseEntity.status(HttpStatus.OK).body(patientDashBoardDto);
    }

}
