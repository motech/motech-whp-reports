package org.motechproject.whp.reports.date;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.motechproject.model.DayOfWeek;
import org.motechproject.util.DateUtil;

import java.sql.Timestamp;
import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class WHPDateTimeTest {
    @Test
    public void shouldReturnDayOfWeek() {
        DateTime tuesday = new DateTime(2013, 01, 01, 0, 0);
        assertEquals(DayOfWeek.Tuesday, WHPDateTime.dayOfWeek(tuesday));
    }

    @Test
    public void shouldConvertStringToTimeStamp() {
        assertEquals(WHPDateTime.datetime("08/01/2013 10:32:35").time(), WHPDateTime.timestamp("08/01/2013 10:32:35"));
        assertNull(WHPDateTime.timestamp(null));
        assertNull(WHPDateTime.timestamp(""));
        assertNull(WHPDateTime.timestamp(" "));
    }

    @Test
    public void shouldConvertToSQLDate() {
        DateTime now = DateUtil.now();
        long currentTimeInMillis = now.getMillis();
        Assert.assertEquals(new Date(currentTimeInMillis), WHPDateTime.toSqlDate(now));
    }

    @Test
    public void shouldConvertToSQLTimestamp() {
        DateTime now = DateUtil.now();
        long currentTimeInMillis = now.getMillis();
        Assert.assertEquals(new Timestamp(currentTimeInMillis), WHPDateTime.toSqlTimestamp(now));
    }
}
