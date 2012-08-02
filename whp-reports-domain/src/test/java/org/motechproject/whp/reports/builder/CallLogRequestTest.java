package org.motechproject.whp.reports.builder;

import org.junit.Test;
import org.motechproject.whp.reports.contract.CallLogRequest;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
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

        CallLog callLog = DomainBuilder.buildCallLog(callLogRequest);
        assertThat(callLog.getCalledBy(), is(callLogRequest.getCalledBy()));
        assertThat(callLog.getEndTime(), is(new DateDimension(callLogRequest.getEndTime())));
        assertThat(callLog.getStartTime(), is(new DateDimension(callLogRequest.getEndTime())));
        assertThat(callLog.getProviderId(), is(callLogRequest.getProviderId()));
    }
}
