package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.motechproject.whp.reports.contract.AdherenceCallLogRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationCallLogRequest;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.domain.measure.FlashingLog;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DomainMapperTest {

    @Test
    public void shouldCreateCallLog() {
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;
        AdherenceCallLogRequest callLogRequest = new AdherenceCallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(startTime.toDate());
        callLogRequest.setEndTime(endTime.toDate());
        callLogRequest.setTotalPatients(12);
        callLogRequest.setAdherenceCaptured(4);
        callLogRequest.setAdherenceNotCaptured(8);
        callLogRequest.setCallId("callId");
        callLogRequest.setCallStatus("callStatusValue");

        AdherenceCallLog callLog = new DomainMapper().mapAdherenceCallLog(callLogRequest);

        assertThat(callLog.getCalledBy(), is(callLogRequest.getCalledBy()));
        assertThat(callLog.getStartDate(), is(new Date(callLogRequest.getStartTime().getTime())));
        assertThat(callLog.getStartDateTime(), is(new Timestamp(callLogRequest.getStartTime().getTime())));
        assertThat(callLog.getEndDate(), is(new Date(callLogRequest.getEndTime().getTime())));
        assertThat(callLog.getEndDateTime(), is(new Timestamp(callLogRequest.getEndTime().getTime())));
        assertThat(callLog.getProviderId(), is(callLogRequest.getProviderId()));
        assertThat(callLog.getTotalPatients(), is(callLogRequest.getTotalPatients()));
        assertThat(callLog.getAdherenceCaptured(), is(callLogRequest.getAdherenceCaptured()));
        assertThat(callLog.getAdherenceNotCaptured(), is(callLogRequest.getAdherenceNotCaptured()));
        assertThat(callLog.getCallId(), is(callLogRequest.getCallId()));
        assertThat(callLog.getCallStatus(), is(callLogRequest.getCallStatus()));
        assertThat(callLog.getDuration(), is(600L));
    }

    @Test
    public void shouldCreateContainerRegistrationCallLog() {
        ContainerRegistrationCallLogRequest request = new ContainerRegistrationCallLogRequest();
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;

        request.setCallId("callId");
        request.setDisconnectionType("disconnectionType");
        request.setEndDateTime("10/12/2012 12:33:35");
        request.setStartDateTime("10/12/2012 12:32:35");
        request.setProviderId("providerid");
        request.setMobileNumber("1234567890");


        ContainerRegistrationCallLog callLog = new DomainMapper().mapContainerRegistrationCallLog(request);

        assertThat(callLog.getCallId(), is(request.getCallId()));
        assertThat(callLog.getDisconnectionType(), is(request.getDisconnectionType()));
        assertThat(callLog.getStartDateTime(), is(toDate(request.getStartDateTime())));
        assertThat(callLog.getEndDateTime(), is(toDate(request.getEndDateTime())));
        assertThat(callLog.getDuration(), is(60L));
        assertThat(callLog.getProviderId(), is(request.getProviderId()));
        assertThat(callLog.getMobileNumber(), is(request.getMobileNumber()));
    }

    @Test
    public void shouldCreateFlashingLog() {
        FlashingLogRequest flashingLogRequest = new FlashingLogRequest();
        flashingLogRequest.setProviderId("ABC");
        flashingLogRequest.setCallTime((new DateTime()).toDate());
        flashingLogRequest.setCreationTime((new DateTime()).toDate());
        flashingLogRequest.setMobileNumber("1234567890");

        FlashingLog flashingLog = new DomainMapper().buildFlashingRequestLog(flashingLogRequest);

        assertThat(flashingLog.getCallTime(), is(flashingLogRequest.getCallTime()));
        assertThat(flashingLog.getMobileNumber(), is(flashingLogRequest.getMobileNumber()));
        assertThat(flashingLog.getProviderId(), is(flashingLogRequest.getProviderId()));
    }

    private java.util.Date toDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/YYYY HH:mm:ss");
        return dateTimeFormatter.parseDateTime(date).toDate();
    }
}
