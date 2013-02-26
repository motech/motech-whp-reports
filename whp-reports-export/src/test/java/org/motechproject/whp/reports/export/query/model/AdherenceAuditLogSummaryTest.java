package org.motechproject.whp.reports.export.query.model;

import org.junit.Test;
import org.motechproject.whp.reports.contract.enums.YesNo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdherenceAuditLogSummaryTest {
    @Test
    public void shouldReturnStringYesNo() {
        AdherenceAuditLogSummary adherenceAuditLogSummary = new AdherenceAuditLogSummary();
        adherenceAuditLogSummary.setIsGivenByProvider(YesNo.Yes.code());
        assertThat(adherenceAuditLogSummary.getIsGivenByProvider(), is(YesNo.Yes.name()));

        adherenceAuditLogSummary.setIsGivenByProvider(YesNo.No.code());
        assertThat(adherenceAuditLogSummary.getIsGivenByProvider(), is(YesNo.No.name()));
    }
}
