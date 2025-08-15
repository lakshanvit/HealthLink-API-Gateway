package com.healthlink.observation.repository;

import com.healthlink.observation.entity.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObservationRepository extends JpaRepository<Observation, Long> {

    Optional<Observation> findByPatientNo(String patientNo);
}
