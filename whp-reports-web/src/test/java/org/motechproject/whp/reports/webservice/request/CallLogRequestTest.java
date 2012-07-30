package org.motechproject.whp.reports.webservice.request;

import org.junit.Test;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CallLogRequestTest {

    @Test
    public void shouldCreateCallLog() {
        CallLogRequest callLogRequest = new CallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(new Date());
        callLogRequest.setEndTime(new Date());

        CallLog callLog = callLogRequest.createCallLog();
        assertThat(callLog.getCalledBy(), is(callLogRequest.getCalledBy()));
        assertThat(callLog.getEndTime(), is(new DateTimeDimension(callLogRequest.getEndTime())));
        assertThat(callLog.getStartTime(), is(new DateTimeDimension(callLogRequest.getEndTime())));
        assertThat(callLog.getProviderId(), is(callLogRequest.getProviderId()));
    }
}
