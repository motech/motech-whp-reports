package org.motechproject.whp.reports.domain.measure;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;

import static junit.framework.Assert.assertEquals;

public class CallLogTest {

    @Test
    public void shouldNotSetDurationInSecondWhenStartTimeIsUnknown() {
        CallLog callLog = new CallLog();
        callLog.setEndTime(new DateTimeDimension(new DateTime(2011, 1, 1, 10, 10, 20).toDate()));
        assertEquals(0, callLog.getDurationInSeconds());
    }

    @Test
    public void shouldNotSetDurationInSecondWhenEndTimeIsUnknown() {
        CallLog callLog = new CallLog();
        callLog.setStartTime(new DateTimeDimension(new DateTime(2011, 1, 1, 10, 10, 20).toDate()));
        assertEquals(0, callLog.getDurationInSeconds());
    }

    @Test
    public void shouldSetDurationInSecondWhenBothEndTimeAndStartTimeAreKnown() {
        CallLog callLog = new CallLog();
        callLog.setStartTime(new DateTimeDimension(new DateTime(2011, 1, 1, 10, 10, 10).toDate()));
        callLog.setEndTime(new DateTimeDimension(new DateTime(2011, 1, 1, 10, 10, 20).toDate()));
        assertEquals(10, callLog.getDurationInSeconds());
    }
}
