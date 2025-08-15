package com.healthlink.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDashBoardDto {

    @NotEmpty(message = "First Name can not be a null or empty")
    private String firstName;

    @NotEmpty(message = "Last Name can not be a null or empty")
    private String lastName;

    @NotEmpty(message = "birthDate can not be a null or empty")
    private LocalDate birthDate;

    @NotEmpty(message = "gender can not be a null or empty")
    private String gender;

    private EncounterDto encounterDto;

    private ObservationDto observationDto;
}
