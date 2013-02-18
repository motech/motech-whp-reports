package org.motechproject.whp.reports.export.query.dao;

import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

public class PredicateBuilderFactory {
    public PredicateBuilder getBuilder(PatientReportRequest patientReportRequest) {
        if (patientReportRequest.getReportType() == PatientReportType.CLOSED_TREATMENT){
            return new PatientCloseTreatmentPredicateBuilder(patientReportRequest);
        }

        if (patientReportRequest.getReportType() == PatientReportType.SUMMARY_REPORT){
            return new PatientSummaryPredicateBuilder(patientReportRequest);
        }
        return null;
    }
}
