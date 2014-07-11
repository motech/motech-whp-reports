package org.motechproject.whp.reports.export.query.dao.query.builder;

import org.motechproject.whp.reports.export.query.dao.PatientReportType;
import org.motechproject.whp.reports.export.query.dao.ProviderReportType;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class PredicateBuilderFactory {
    public PredicateBuilder getBuilder(PatientReportRequest patientReportRequest) {
    	if(patientReportRequest.getReportType() != null){
        if (patientReportRequest.getReportType() == PatientReportType.CLOSED_TREATMENT){
            return new PatientCloseTreatmentPredicateBuilder(patientReportRequest);
        }

        else if (patientReportRequest.getReportType() == PatientReportType.SUMMARY_REPORT){
            return new PatientSummaryPredicateBuilder(patientReportRequest);
        }
        
        else if(patientReportRequest.getReportType().equals(PatientReportType.CALL_LOG)){
        	return new PatientCallLogSummaryPredicateBuilder(patientReportRequest);
        }
    	}
        return null;
    }

	public PredicateBuilder getBuilder(ProviderReportRequest providerReportRequest) {
		if(providerReportRequest.getReportType() != null){
	        if (providerReportRequest.getReportType() == ProviderReportType.REMINDER_CALL_LOG){
	            return new ProviderReminderCallLogSummaryPredicateBuilder(providerReportRequest);
	    	}
		}
	    return null;
	}
}
