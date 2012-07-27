package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.motechproject.whp.reports.repository.AllPatientAdherenceSubmissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PatientAdherenceSubmissionService {

    private AllPatientAdherenceSubmissions adherenceSubmissions;

    /*Required for spring proxy*/
    PatientAdherenceSubmissionService() {
    }

    @Autowired
    public PatientAdherenceSubmissionService(AllPatientAdherenceSubmissions adherenceSubmissions) {
        this.adherenceSubmissions = adherenceSubmissions;
    }

    public void save(PatientAdherenceSubmission adherenceSubmission) {
        adherenceSubmissions.save(adherenceSubmission);
    }
}
