package com.healthlink.encounter.service;

import com.healthlink.encounter.dto.EncounterDto;

public interface IEncounterService {

    EncounterDto fetchLastVisit(String patientNo);
}
