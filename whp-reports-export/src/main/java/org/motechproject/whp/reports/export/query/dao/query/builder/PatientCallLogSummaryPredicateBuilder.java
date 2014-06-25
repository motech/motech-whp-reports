package org.motechproject.whp.reports.export.query.dao.query.builder;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.motechproject.whp.reports.export.query.model.DateRange;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;

public class PatientCallLogSummaryPredicateBuilder implements PredicateBuilder {
	
	private PatientReportRequest patientReportRequest;

    public PatientCallLogSummaryPredicateBuilder(PatientReportRequest patientReportRequest) {
        this.patientReportRequest = patientReportRequest;
    }   
	@Override
	public List<String> getPredicates() {
		DateRange dateRange = new DateRange(patientReportRequest.getFrom(), patientReportRequest.getTo());
        if(dateRange.hasValidRange())
            return asList(String.format(" call_log.attempt_date_time between '%s' AND '%s'",
                    dateRange.getStartDateInSqlFormat(),
                    dateRange.getEndDateInSqlFormat()));
        else return new ArrayList<>();
	}

}
