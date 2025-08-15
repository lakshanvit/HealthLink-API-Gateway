package com.healthlink.observation.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ObservationDto {

    private String bpvalue;
    private String bpunit;
    private String patientNo;
}
