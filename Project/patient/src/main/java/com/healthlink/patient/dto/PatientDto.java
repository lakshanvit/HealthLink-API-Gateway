package com.healthlink.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {

    @NotEmpty(message = "First Name can not be a null or empty")
    private String firstName;

    @NotEmpty(message = "Last Name can not be a null or empty")
    private String lastName;

    @NotEmpty(message = "birthDate can not be a null or empty")
    private LocalDate birthDate;

    @NotEmpty(message = "gender can not be a null or empty")
    private String gender;

    @NotEmpty(message = "patientNo can not be a null or empty")
    private String patientNo;

    @NotEmpty(message = "email can not be a null or empty")
    private String email;

    @NotEmpty(message = "mobileNo can not be a null or empty")
    private String mobileNo;
}
