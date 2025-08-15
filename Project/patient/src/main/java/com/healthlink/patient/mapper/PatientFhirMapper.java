package com.healthlink.patient.mapper;

import com.healthlink.patient.entity.Patient;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class PatientFhirMapper {
    public org.hl7.fhir.r4.model.Patient toFhirResource(Patient patient) {
        org.hl7.fhir.r4.model.Patient fhirPatient = new org.hl7.fhir.r4.model.Patient();

        // 1. Set public ID (UUID)
        fhirPatient.setId(patient.getPublicId());

        // 2. Add MRN identifier
        Identifier mrnIdentifier = new Identifier();
        mrnIdentifier.setSystem("http://healthlink.org/mrn");
        mrnIdentifier.setValue(patient.getPatientNo());
        fhirPatient.addIdentifier(mrnIdentifier);

        // 3. Map name
        HumanName name = fhirPatient.addName();
        name.setFamily(patient.getLastName());
        name.addGiven(patient.getFirstName());

        // 4. Map birth date (handle nulls)
        if(patient.getBirthDate() != null) {
            LocalDate localDate = patient.getBirthDate();
            DateType fhirDate = new DateType(localDate.toString());
            fhirPatient.setBirthDateElement(fhirDate);
        }

        // 5. Map gender (e.g., "male" → Enumerations.AdministrativeGender.MALE)
        fhirPatient.setGender(mapGender(patient.getGender()));

        return fhirPatient;

    }
     private Enumerations.AdministrativeGender mapGender(String genderCode) {
        return switch (genderCode.toLowerCase()) {
            case "male" -> Enumerations.AdministrativeGender.MALE;
            case  "female" -> Enumerations.AdministrativeGender.FEMALE;
            default -> Enumerations.AdministrativeGender.UNKNOWN;
        };
     }

     public Patient toPatientEntity(org.hl7.fhir.r4.model.Patient fhirPatient) {
        Patient patient = new Patient();

         // Name
         if(!fhirPatient.getName().isEmpty()) {
             HumanName name = fhirPatient.getNameFirstRep();
             patient.setFirstName(name.getGivenAsSingleString());
             patient.setLastName(name.getFamily());
         }

         // Birth Date
         if (fhirPatient.hasBirthDate()) {
             patient.setBirthDate(fhirPatient.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
         }

         // Gender
         if(fhirPatient.hasGender()) {
             patient.setGender(fhirPatient.getGender().toCode());
         }

         return patient;
     }
}
