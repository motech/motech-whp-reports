package org.motechproject.whp.reports.export.query.dao.query.builder;

import org.motechproject.whp.reports.export.query.dao.PatientReportType;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

public class PredicateBuilderFactory {
    public PredicateBuilder getBuilder(PatientReportRequest patientReportRequest) {
        if (patientReportRequest.getReportType() == PatientReportType.CLOSED_TREATMENT){
            return new PatientCloseTreatmentPredicateBuilder(patientReportRequest);
        }

        else if (patientReportRequest.getReportType() == PatientReportType.SUMMARY_REPORT){
            return new PatientSummaryPredicateBuilder(patientReportRequest);
        }
        
        else if(patientReportRequest.getReportType().equals(PatientReportType.CALL_LOG)){
        	return new PatientCallLogSummaryPredicateBuilder(patientReportRequest);
        }
        return null;
    }
}
