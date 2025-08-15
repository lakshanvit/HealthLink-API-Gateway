package com.healthlink.encounter.mapper;

import com.healthlink.encounter.dto.EncounterDto;
import com.healthlink.encounter.entity.Encounter;

public class EncounterMapper {

    public static EncounterDto mapToEncounterDto(Encounter encounter, EncounterDto encounterDto) {
        encounterDto.setVisitDate(encounter.getVisitDate());
        encounterDto.setReason(encounter.getReason());
        encounterDto.setPatientNo(encounter.getPatientNo());

        return encounterDto;
    }

    public static Encounter mapToEncounter(EncounterDto encounterDto, Encounter encounter) {
        encounter.setVisitDate(encounterDto.getVisitDate());
        encounter.setReason(encounterDto.getReason());
        encounter.setPatientNo(encounterDto.getPatientNo());

        return encounter;
    }
}
