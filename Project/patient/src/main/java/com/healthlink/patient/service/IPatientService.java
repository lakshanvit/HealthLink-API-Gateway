package com.healthlink.patient.service;

import com.healthlink.patient.dto.PatientDashBoardDto;
import com.healthlink.patient.entity.Patient;

public interface IPatientService {
    PatientDashBoardDto fetchDashBoardDetails(String patientNo);

    Patient createPatient(org.hl7.fhir.r4.model.Patient fhirPatient);

    org.hl7.fhir.r4.model.Patient getFhirPatient(String publicId);
}
