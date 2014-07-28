package org.motechproject.whp.reports.export.query.dao;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.motechproject.whp.reports.export.query.dao.query.builder.ProviderReminderCallLogSummaryPredicateBuilder;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class ProviderReminderCallLogSummaryPredicateBuilderTest {

	@Test
    public void shouldReturnPredicateToFilterByDate(){
        ProviderReportRequest providerReportRequest = new ProviderReportRequest();
        providerReportRequest.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        providerReportRequest.setFrom("25/12/2012");
        providerReportRequest.setTo("25/12/2013");
        ProviderReminderCallLogSummaryPredicateBuilder providerReminderCallLogSummaryPredicateBuilder = new ProviderReminderCallLogSummaryPredicateBuilder(providerReportRequest);
        
        List<String> expectedPredicates = asList(" call_log.attempt_date_time between '2012-12-25' AND '2013-12-26'");
        assertThat(providerReminderCallLogSummaryPredicateBuilder.getPredicates(), is(expectedPredicates));
    }

    @Test
    public void shouldReturnPredicateWhenDateRangeIsNotGiven(){
    	ProviderReportRequest providerReportRequest = new ProviderReportRequest();
        providerReportRequest.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        providerReportRequest.setFrom(null);
        providerReportRequest.setTo(null);
        ProviderReminderCallLogSummaryPredicateBuilder providerReminderCallLogSummaryPredicateBuilder = new ProviderReminderCallLogSummaryPredicateBuilder(providerReportRequest);
        
        List<String> expectedPredicates = new ArrayList<>();
        assertThat(providerReminderCallLogSummaryPredicateBuilder.getPredicates(), is(expectedPredicates));
    }
}
