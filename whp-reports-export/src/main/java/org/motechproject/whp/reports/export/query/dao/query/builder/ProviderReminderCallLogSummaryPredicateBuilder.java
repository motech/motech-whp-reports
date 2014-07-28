package org.motechproject.whp.reports.export.query.dao.query.builder;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.motechproject.whp.reports.export.query.model.DateRange;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class ProviderReminderCallLogSummaryPredicateBuilder implements PredicateBuilder {
	
	private ProviderReportRequest providerReportRequest;

    public ProviderReminderCallLogSummaryPredicateBuilder(ProviderReportRequest providerReportRequest) {
        this.providerReportRequest = providerReportRequest;
    }   
	@Override
	public List<String> getPredicates() {
		DateRange dateRange = new DateRange(providerReportRequest.getFrom(), providerReportRequest.getTo(), true);
        if(dateRange.hasValidRange())
            return asList(String.format(" call_log.attempt_date_time between '%s' AND '%s'",
                    dateRange.getStartDateInSqlFormat(),
                    dateRange.getEndDateInSqlFormat()));
        else return new ArrayList<>();
	}
}
