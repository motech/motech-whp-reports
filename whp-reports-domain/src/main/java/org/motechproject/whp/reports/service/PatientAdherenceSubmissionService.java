package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.motechproject.whp.reports.repository.PatientAdherenceSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientAdherenceSubmissionService {

    private PatientAdherenceSubmissionRepository patientAdherenceSubmissionRepository;

    /*Required for spring proxy*/
    PatientAdherenceSubmissionService() {
    }

    @Autowired
    public PatientAdherenceSubmissionService(PatientAdherenceSubmissionRepository patientAdherenceSubmissionRepository) {
        this.patientAdherenceSubmissionRepository = patientAdherenceSubmissionRepository;
    }

    public void save(PatientAdherenceSubmission adherenceSubmission) {
        patientAdherenceSubmissionRepository.save(adherenceSubmission);
    }
}
