package org.motechproject.whp.reports.date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.model.DayOfWeek;

import static junit.framework.Assert.assertEquals;

public class WHPDateTimeTest {
    @Test
    public void shouldReturnDayOfWeek() {
        DateTime tuesday = new DateTime(2013, 01, 01, 0, 0);
        assertEquals(DayOfWeek.Tuesday, WHPDateTime.dayOfWeek(tuesday));
    }
}
