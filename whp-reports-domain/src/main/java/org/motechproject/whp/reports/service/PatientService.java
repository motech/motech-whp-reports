package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient getPatient(String patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
