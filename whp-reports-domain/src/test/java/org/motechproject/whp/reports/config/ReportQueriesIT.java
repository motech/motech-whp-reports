package org.motechproject.whp.reports.config;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class ReportQueriesIT extends IntegrationTest {

    @Autowired
    ReportQueries reportQueries;

    @Test
    public void shouldReturnReportQueries() {
        assertNotNull(reportQueries.getAdherenceAuditLogReportQuery());
        assertNotNull(reportQueries.getAdherenceDataReportQuery());
        assertNotNull(reportQueries.getProviderReminderCallLogQuery());
        assertNotNull(reportQueries.getContainerRecordQuery());
    }

}
