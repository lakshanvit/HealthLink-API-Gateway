package com.healthlink.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EncounterDto {

    @NotEmpty(message = "ID can not be a null or empty")
    private String id;

    private LocalDate lastVisit;

    private String reason;

}
