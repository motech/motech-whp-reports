package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.PatientAdherenceSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllPatientAdherenceSubmissions {

    @Autowired
    private DataAccessTemplate template;

    public void save(PatientAdherenceSubmission adherenceSubmission) {
        template.saveOrUpdate(adherenceSubmission);
    }
}
