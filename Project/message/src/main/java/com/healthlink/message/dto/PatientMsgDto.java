package com.healthlink.message.dto;

/**
 * @param patientId
 * @param name
 * @param email
 * @param mobileNo
 */
public record PatientMsgDto(Long patientId, String name, String email, String mobileNo ) {
}
