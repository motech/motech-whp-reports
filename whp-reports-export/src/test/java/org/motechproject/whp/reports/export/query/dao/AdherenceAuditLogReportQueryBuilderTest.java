package org.motechproject.whp.reports.export.query.dao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdherenceAuditLogReportQueryBuilderTest {
    
    @Test
    public void shouldReturnAdherenceLogReportWhenAdherenceValueSubmittedByProvider(){

        AdherenceAuditLogReportQueryBuilder adherenceAuditLogReportQueryBuilder = new AdherenceAuditLogReportQueryBuilder();
        String expectedQuery = AdherenceAuditLogReportQueryBuilder.ADHERENCE_AUDIT_LOG_SQL +
                AdherenceAuditLogReportQueryBuilder.WHERE_CLAUSE +
                adherenceAuditLogReportQueryBuilder.getLIMIT_TO_THREE_MONTHS() +
                AdherenceAuditLogReportQueryBuilder.ORDER_BY +
                AdherenceAuditLogReportQueryBuilder.LIMIT;
        assertThat(adherenceAuditLogReportQueryBuilder.build(), is(expectedQuery));
    }

}
