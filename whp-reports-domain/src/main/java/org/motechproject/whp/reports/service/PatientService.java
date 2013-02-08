package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.patient.PatientDTO;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.mapper.PatientMapper;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    PatientService() {
    }

    @Autowired
    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public Patient getPatient(String patientId) {
        return patientRepository.findByPatientId(patientId);
    }

    public void update(PatientDTO patientDTO) {
        Patient patient = patientRepository.findByPatientId(patientDTO.getPatientId());
        patientMapper.map(patientDTO, patient);
        patientRepository.save(patient);
    }
}
