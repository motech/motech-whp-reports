package org.motechproject.whp.reports.export.query.service;

import org.motechproject.whp.reports.export.query.dao.ReportQueryDAO;
import org.motechproject.whp.reports.export.query.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ReportQueryService {

    private ReportQueryDAO reportQueryDAO;

    ReportQueryService() {
    }

    @Autowired
    public ReportQueryService(ReportQueryDAO reportQueryDAO) {
        this.reportQueryDAO = reportQueryDAO;
    }

    public List<PatientSummary> getPatientSummaries(PatientReportRequest patientReportRequest) {
        return reportQueryDAO.getPatientSummaries(patientReportRequest);
    }

    public List<AdherenceAuditLogSummary> getAdherenceAuditLogSummaries() {
        return reportQueryDAO.getAdherenceAuditLogSummaries();
    }

    public List<AdherenceRecordSummary> getAdherenceRecordSummaries() {
        return reportQueryDAO.getAdherenceRecordSummaries();
    }

    public List<ProviderReminderCallLogSummary> getProviderReminderCallLogSummaries() {
        return reportQueryDAO.getProviderReminderCallLogSummaries();
    }
    
    public List<PatientReminderCallLogSummary> getPatientReminderCallLogSummaries(PatientReportRequest patientReportRequest) {
        return reportQueryDAO.getPatientReminderCallLogSummaries(patientReportRequest);
    }

}
