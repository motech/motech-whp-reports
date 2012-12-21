package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.domain.measure.FlashingLog;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DomainMapperTest {

    private DomainMapper domainMapper;

    @Before
    public void setUp() {
        domainMapper = new DomainMapper();
    }

    @Test
    public void shouldCreateCallLog() {
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;
        DateTime attemptTime = now.minusDays(2);
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(startTime.toDate());
        callLogRequest.setEndTime(endTime.toDate());
        callLogRequest.setTotalPatients(12);
        callLogRequest.setAttemptTime(attemptTime.toDate());
        callLogRequest.setCallAnswered("callAnswered");
        callLogRequest.setAdherenceCaptured(4);
        callLogRequest.setAdherenceNotCaptured(8);
        callLogRequest.setCallId("callId");
        callLogRequest.setCallStatus("callStatusValue");
        callLogRequest.setFlashingCallId("abcd1234");

        AdherenceCallLog callLog = domainMapper.mapAdherenceCallLog(callLogRequest);

        assertThat(callLog.getCalledBy(), is(callLogRequest.getCalledBy()));
        assertThat(callLog.getStartDate(), is(new Date(callLogRequest.getStartTime().getTime())));
        assertThat(callLog.getStartDateTime(), is(new Timestamp(callLogRequest.getStartTime().getTime())));
        assertThat(callLog.getEndDate(), is(new Date(callLogRequest.getEndTime().getTime())));
        assertThat(callLog.getEndDateTime(), is(new Timestamp(callLogRequest.getEndTime().getTime())));
        assertThat(callLog.getProviderId(), is(callLogRequest.getProviderId()));
        assertThat(callLog.getAttemptTime(), is(callLogRequest.getAttemptTime()));
        assertThat(callLog.getTotalPatients(), is(callLogRequest.getTotalPatients()));
        assertThat(callLog.getAdherenceCaptured(), is(callLogRequest.getAdherenceCaptured()));
        assertThat(callLog.getAdherenceNotCaptured(), is(callLogRequest.getAdherenceNotCaptured()));
        assertThat(callLog.getCallId(), is(callLogRequest.getCallId()));
        assertThat(callLog.getFlashingCallId(), is(callLogRequest.getFlashingCallId()));
        assertThat(callLog.getCallStatus(), is(callLogRequest.getCallStatus()));
        assertThat(callLog.getCallAnswered(), is(callLogRequest.getCallAnswered()));
        assertThat(callLog.getDuration(), is(600L));
    }

    @Test
    public void shouldCreateFlashingLog() {
        FlashingLogRequest flashingLogRequest = new FlashingLogRequest();
        flashingLogRequest.setProviderId("ABC");
        flashingLogRequest.setCallTime((new DateTime()).toDate());
        flashingLogRequest.setFlashingCallId("abcd1234");
        flashingLogRequest.setCreationTime((new DateTime()).toDate());
        flashingLogRequest.setMobileNumber("1234567890");

        FlashingLog flashingLog = domainMapper.buildFlashingRequestLog(flashingLogRequest);

        assertThat(flashingLog.getCallTime(), is(flashingLogRequest.getCallTime()));
        assertThat(flashingLog.getMobileNumber(), is(flashingLogRequest.getMobileNumber()));
        assertThat(flashingLog.getProviderId(), is(flashingLogRequest.getProviderId()));
        assertThat(flashingLog.getFlashingCallId(), is(flashingLogRequest.getFlashingCallId()));
    }
}
