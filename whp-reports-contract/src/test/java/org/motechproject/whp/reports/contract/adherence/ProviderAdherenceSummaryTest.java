package org.motechproject.whp.reports.contract.adherence;

import org.joda.time.LocalDate;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ProviderAdherenceSummaryTest {

    @Test
    public void shouldIncrementAdherenceMissingWeeksBasedOnWeeklyAdherenceStatus() {
        ProviderAdherenceSummary providerAdherenceSummary = new ProviderAdherenceSummary();

        assertEquals(0, providerAdherenceSummary.getAdherenceMissingWeeks());

        LocalDate someDate = new LocalDate();

        providerAdherenceSummary.addWeeklyAdherenceStatus(new WeeklyAdherenceStatus(someDate, someDate, true));
        assertEquals(0, providerAdherenceSummary.getAdherenceMissingWeeks());

        providerAdherenceSummary.addWeeklyAdherenceStatus(new WeeklyAdherenceStatus(someDate, someDate, false));
        assertEquals(1, providerAdherenceSummary.getAdherenceMissingWeeks());

        providerAdherenceSummary.addWeeklyAdherenceStatus(new WeeklyAdherenceStatus(someDate, someDate, true));
        assertEquals(1, providerAdherenceSummary.getAdherenceMissingWeeks());

        providerAdherenceSummary.addWeeklyAdherenceStatus(new WeeklyAdherenceStatus(someDate, someDate, false));
        assertEquals(2, providerAdherenceSummary.getAdherenceMissingWeeks());
    }
}
