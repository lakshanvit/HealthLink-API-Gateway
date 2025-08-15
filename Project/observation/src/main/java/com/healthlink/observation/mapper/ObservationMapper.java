package com.healthlink.observation.mapper;

import com.healthlink.observation.dto.ObservationDto;
import com.healthlink.observation.entity.Observation;

public class ObservationMapper {

    public static ObservationDto mapToObservationDto(Observation observation, ObservationDto observationDto) {
        observationDto.setBpvalue(observation.getBpvalue());
        observationDto.setBpunit(observation.getBpunit());
        observationDto.setPatientNo(observation.getPatientNo());

        return observationDto;
    }

    public static Observation mapToObservation(ObservationDto observationDto, Observation observation) {
        observation.setBpvalue(observationDto.getBpvalue());
        observation.setBpunit(observationDto.getBpunit());
        observation.setPatientNo(observationDto.getPatientNo());

        return observation;
    }
}
