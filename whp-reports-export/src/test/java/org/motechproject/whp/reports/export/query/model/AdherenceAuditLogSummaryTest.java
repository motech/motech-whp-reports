package org.motechproject.whp.reports.export.query.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdherenceAuditLogSummaryTest {

    @Test
    public void shouldReturnBlankDistrictIfNumberOfDosesTakenIsEmpty(){
        AdherenceAuditLogSummary adherenceAuditLogSummary = new AdherenceAuditLogSummary();
        adherenceAuditLogSummary.setDistrict("district");
        adherenceAuditLogSummary.setNumberOfDosesTaken(null);

        assertThat(adherenceAuditLogSummary.getDistrict(), is(""));

        adherenceAuditLogSummary.setNumberOfDosesTaken(2);
        assertThat(adherenceAuditLogSummary.getDistrict(), is("district"));

        adherenceAuditLogSummary.setNumberOfDosesTaken(0);
        assertThat(adherenceAuditLogSummary.getDistrict(), is("district"));
    }


}
