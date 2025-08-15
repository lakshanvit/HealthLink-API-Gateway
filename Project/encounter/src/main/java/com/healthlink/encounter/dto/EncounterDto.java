package com.healthlink.encounter.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EncounterDto {

    private LocalDate visitDate;
    private String reason;
    private String patientNo;

}
