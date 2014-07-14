package org.motechproject.whp.reports.export.query.dao;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.motechproject.whp.reports.export.query.dao.query.builder.PatientReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.dao.query.builder.ProviderReportsQueryBuilder;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;

public class ProviderReportsQueryBuilderTest {

	@Test
    public void shouldReturnQueryForProviderReminderCallLogWithoutAnyUserGivenFilters() {
        ProviderReportRequest emptyRequest = new ProviderReportRequest();
        emptyRequest.setReportType(ProviderReportType.REMINDER_CALL_LOG);

        String expectedQuery = String.format(ProviderReportsQueryBuilder.PROVIDER_CALLLOG_SELECT_SQL
        		+ ProviderReportsQueryBuilder.WHERE_CLAUSE
                + ProviderReportsQueryBuilder.DEFAULT_TEST_DISTRICT_FILTER
                + ProviderReportsQueryBuilder.PROVIDER_CALL_LOG_SUMMARY_SORT_SQL);

        assertEquals(expectedQuery, new ProviderReportsQueryBuilder(emptyRequest).build());
    }

    @Test
    public void shouldReturnQueryForProviderReminderCallLogWithDistrict() {
        String expectedQuery = String.format(ProviderReportsQueryBuilder.PROVIDER_CALLLOG_SELECT_SQL
        		+ ProviderReportsQueryBuilder.WHERE_CLAUSE
                + ProviderReportsQueryBuilder.DEFAULT_TEST_DISTRICT_FILTER
                + " AND provider.district = 'Begusarai'"
                + ProviderReportsQueryBuilder.PROVIDER_CALL_LOG_SUMMARY_SORT_SQL);

        ProviderReportRequest requestWithDistrict = new ProviderReportRequest();
        requestWithDistrict.setDistrict("Begusarai");
        requestWithDistrict.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        assertEquals(expectedQuery, new ProviderReportsQueryBuilder(requestWithDistrict).build());
    }

    @Test
    public void shouldReturnQueryForProviderReminderCallLogBetweenGivenDates() {
        String expectedQuery = ProviderReportsQueryBuilder.PROVIDER_CALLLOG_SELECT_SQL
        		+ ProviderReportsQueryBuilder.WHERE_CLAUSE
                + ProviderReportsQueryBuilder.DEFAULT_TEST_DISTRICT_FILTER
                + " AND call_log.attempt_date_time between '2013-01-01' AND '2013-01-02'"
                + ProviderReportsQueryBuilder.PROVIDER_CALL_LOG_SUMMARY_SORT_SQL;

        ProviderReportRequest requestWithDates = new ProviderReportRequest();
        requestWithDates.setFrom("01/01/2013");
        requestWithDates.setTo("02/01/2013");
        requestWithDates.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        assertEquals(expectedQuery, new ProviderReportsQueryBuilder(requestWithDates).build());
    }

    @Test
    public void shouldReturnQueryForPProviderReminderCallLogWithGivenDistrictAndBetweenGivenDates() {
    	String expectedQuery = ProviderReportsQueryBuilder.PROVIDER_CALLLOG_SELECT_SQL
        		+ ProviderReportsQueryBuilder.WHERE_CLAUSE
                + ProviderReportsQueryBuilder.DEFAULT_TEST_DISTRICT_FILTER
                + " AND provider.district = 'Begusarai'"
                + " AND call_log.attempt_date_time between '2013-01-01' AND '2013-01-02'"
                + ProviderReportsQueryBuilder.PROVIDER_CALL_LOG_SUMMARY_SORT_SQL;

        ProviderReportRequest request = new ProviderReportRequest();
        request.setDistrict("Begusarai");
        request.setFrom("01/01/2013");
        request.setTo("02/01/2013");
        request.setReportType(ProviderReportType.REMINDER_CALL_LOG);
        assertEquals(expectedQuery, new ProviderReportsQueryBuilder(request)
                .build());
    }
}
