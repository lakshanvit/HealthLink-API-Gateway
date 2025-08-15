package com.healthlink.encounter.service.impl;

import com.healthlink.encounter.dto.EncounterDto;
import com.healthlink.encounter.entity.Encounter;
import com.healthlink.encounter.exception.ResourceNotFoundException;
import com.healthlink.encounter.mapper.EncounterMapper;
import com.healthlink.encounter.repository.EncounterRepository;
import com.healthlink.encounter.service.IEncounterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EncounterServiceImpl implements IEncounterService {

    private EncounterRepository encounterRepository;

    @Override
    public EncounterDto fetchLastVisit(String patientNo) {
        Encounter encounter = encounterRepository.findLastVisitByPatientNo(patientNo).orElseThrow(
                () -> new ResourceNotFoundException("Encounter", "patientNo", patientNo)
        );
        return EncounterMapper.mapToEncounterDto(encounter, new EncounterDto());
    }
}
