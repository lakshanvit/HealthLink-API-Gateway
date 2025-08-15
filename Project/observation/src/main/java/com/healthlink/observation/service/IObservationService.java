package com.healthlink.observation.service;

import com.healthlink.observation.dto.ObservationDto;

public interface IObservationService {
    ObservationDto fetchObservation(String patientNo);
}
