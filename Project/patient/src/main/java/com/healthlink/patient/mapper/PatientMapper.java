package com.healthlink.patient.mapper;

import com.healthlink.patient.dto.PatientDashBoardDto;
import com.healthlink.patient.dto.PatientDto;
import com.healthlink.patient.entity.Patient;

public class PatientMapper {

    public static PatientDto mapToPatientDto(Patient patient, PatientDto patientDto){
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setBirthDate(patient.getBirthDate());
        patientDto.setGender(patient.getGender());

        return patientDto;
    }

    public static PatientDashBoardDto mapToPatientDashBoardDto(Patient patient, PatientDashBoardDto patientDashBoardDto) {
        patientDashBoardDto.setFirstName(patient.getFirstName());
        patientDashBoardDto.setLastName(patient.getLastName());
        patientDashBoardDto.setBirthDate(patient.getBirthDate());
        patientDashBoardDto.setGender(patient.getGender());

        return patientDashBoardDto;
    }

    public static Patient mapToPatient(PatientDto patientDto, Patient patient){
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setGender(patient.getGender());

        return patient;
    }
}
