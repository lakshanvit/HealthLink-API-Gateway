package com.healthlink.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String patientNo;
    private String statusCode;
    private String statusMsg;
}
