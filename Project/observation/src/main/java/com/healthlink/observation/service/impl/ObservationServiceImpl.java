package com.healthlink.observation.service.impl;

import com.healthlink.observation.dto.ObservationDto;
import com.healthlink.observation.entity.Observation;
import com.healthlink.observation.exception.ResourceNotFoundException;
import com.healthlink.observation.mapper.ObservationMapper;
import com.healthlink.observation.repository.ObservationRepository;
import com.healthlink.observation.service.IObservationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ObservationServiceImpl implements IObservationService {

    private ObservationRepository observationRepository;

    @Override
    public ObservationDto fetchObservation(String patientNo) {
        Observation observation = observationRepository.findByPatientNo(patientNo).orElseThrow(
                () -> new ResourceNotFoundException("Observation", "patientNo", patientNo)
        );
        return ObservationMapper.mapToObservationDto(observation, new ObservationDto());
    }
}
