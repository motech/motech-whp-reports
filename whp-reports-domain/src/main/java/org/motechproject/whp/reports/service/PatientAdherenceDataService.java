package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.query.PatientAdherenceInfo;
import org.motechproject.whp.reports.dao.PatientQueryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientAdherenceDataService {

    private PatientQueryDAO patientQueryDAO;

    PatientAdherenceDataService() {
    }

    @Autowired
    public PatientAdherenceDataService(PatientQueryDAO patientQueryDAO) {
        this.patientQueryDAO = patientQueryDAO;
    }

    public List<PatientAdherenceInfo> getAdherenceInfoForActivePatientsWithMissingAdherence(int skip, int limit) {
        return patientQueryDAO.findPatientDetailsOfActivePatientsWithAdherenceMissing(skip, limit);
    }
}
