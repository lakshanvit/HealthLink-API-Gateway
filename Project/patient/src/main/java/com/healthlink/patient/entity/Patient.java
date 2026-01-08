package com.healthlink.patient.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;

    private String publicId;

    @Column(unique = true)
    private String patientNo;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String gender;

    private String email;

    private String mobileNo;

    @Column(name = "communication_sw")
    private Boolean communicationSw;


}
