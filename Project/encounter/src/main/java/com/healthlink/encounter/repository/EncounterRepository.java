package com.healthlink.encounter.repository;

import com.healthlink.encounter.entity.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {
    @Query("SELECT e FROM Encounter e WHERE e.patientNo = ?1 ORDER BY e.visitDate DESC LIMIT 1 ")
    Optional<Encounter>  findLastVisitByPatientNo (String patientNo);
}
