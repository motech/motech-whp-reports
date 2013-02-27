package org.motechproject.whp.reports.export.query.model;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.model.DayOfWeek;
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.date.WHPDateTime;

import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProviderReminderCallLogSummaryTest {

    ProviderReminderCallLogSummary providerReminderCallLogSummary;

    @Before
    public void setUp() throws Exception {
        providerReminderCallLogSummary = new ProviderReminderCallLogSummary();

    }

    @Test
    public void shouldReturnDurationOfTheReminderCall() {
        long currentTimeInMillis = DateUtil.now().getMillis();

        Timestamp startTime = new Timestamp(currentTimeInMillis);
        Timestamp endTime = new Timestamp(currentTimeInMillis + 2000L);
        providerReminderCallLogSummary.setStartDateTime(startTime);
        providerReminderCallLogSummary.setEndDateTime(endTime);

        assertThat(providerReminderCallLogSummary.getDuration(), is(2));
    }

    @Test
    public void shouldReturnDayOfReminder() {
        Timestamp sundayTimestamp = WHPDateTime.toSqlTimestamp(new DateTime(2013, 2, 24, 0, 0));
        providerReminderCallLogSummary.setAttemptDateTime(sundayTimestamp);

        assertEquals(DayOfWeek.Sunday.name(), providerReminderCallLogSummary.getReminderDay());
    }

    @Test
    public void shouldReturnAdherenceReportedValue() {
        providerReminderCallLogSummary.setAdherenceReported("Y");
        assertThat(providerReminderCallLogSummary.getAdherenceReportedDisplayText(), is("Yes"));

        providerReminderCallLogSummary.setAdherenceReported("N");
        assertThat(providerReminderCallLogSummary.getAdherenceReportedDisplayText(), is("No"));
    }
}
