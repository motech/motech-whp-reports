package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.patient.Patient;

public interface PatientRepository extends MotechJpaRepository<Patient>{
    Patient findByPatientId(String patientId);
}
