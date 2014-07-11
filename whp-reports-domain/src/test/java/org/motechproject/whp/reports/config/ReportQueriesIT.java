package org.motechproject.whp.reports.config;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class ReportQueriesIT extends IntegrationTest {

    @Autowired
    WhpReportQueries whpReportQueries;

    @Test
    public void shouldReturnReportQueries() {
        assertNotNull(whpReportQueries.getAdherenceAuditLogReportQuery());
        assertNotNull(whpReportQueries.getAdherenceDataReportQuery());
        //TODO assertNotNull(whpReportQueries.getProviderReminderCallLogQuery());
    }

}
