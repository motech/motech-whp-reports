package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.contract.CallLogRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationRequest;
import org.motechproject.whp.reports.contract.FlashingLogRequest;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DomainBuilderTest {

    @Test
    public void shouldCreateCallLog() {
        DateTime now = new DateTime();
        DateTime startTime = now.minusMinutes(10);
        DateTime endTime = now;
        CallLogRequest callLogRequest = new CallLogRequest();
        callLogRequest.setCalledBy("caller");
        callLogRequest.setProviderId("providerId");
        callLogRequest.setStartTime(startTime.toDate());
        callLogRequest.setEndTime(endTime.toDate());
        callLogRequest.setTotalPatients(12);
        callLogRequest.setAdherenceCaptured(4);
        callLogRequest.setAdherenceNotCaptured(8);
        callLogRequest.setCallId("callId");
        callLogRequest.setCallStatus("callStatusValue");

        CallLog callLog = DomainBuilder.buildCallLog(callLogRequest);

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
    public void shouldCreateFlashingLog(){
        FlashingLogRequest flashingLogRequest = new FlashingLogRequest();
        flashingLogRequest.setProviderId("ABC");
        flashingLogRequest.setCallTime((new DateTime()).toDate());
        flashingLogRequest.setCreationTime((new DateTime()).toDate());
        flashingLogRequest.setMobileNumber("1234567890");

        FlashingLog flashingLog = DomainBuilder.buildFlashingRequestLog(flashingLogRequest);

        assertThat(flashingLog.getCallTime(), is(flashingLogRequest.getCallTime()));
        assertThat(flashingLog.getMobileNumber(), is(flashingLogRequest.getMobileNumber()));
        assertThat(flashingLog.getProviderId(), is(flashingLogRequest.getProviderId()));
    }

    @Test
    public void shouldCreateSputumTrackingLog(){
        java.util.Date now = new java.util.Date();

        ContainerRegistrationRequest containerRegistrationRequest = new ContainerRegistrationRequest();
        containerRegistrationRequest.setContainerId("containerId");
        containerRegistrationRequest.setInstance("PreTreatment");
        containerRegistrationRequest.setDateIssuedOn(now);
        containerRegistrationRequest.setProviderId("raj");
        containerRegistrationRequest.setSubmitterRole("CmfAdmin");
        containerRegistrationRequest.setSubmitterId("submitterId");

        ContainerRecord containerRecord = DomainBuilder.buildSputumTrackingContainerRegistrationLog(containerRegistrationRequest);

        assertThat(containerRecord.getContainerId(), is(containerRegistrationRequest.getContainerId()));
        assertThat(containerRecord.getInstance(), is(containerRegistrationRequest.getInstance()));
        assertThat(containerRecord.getDateIssuedOn(), is(containerRegistrationRequest.getDateIssuedOn()));
        assertThat(containerRecord.getProviderId(), is(containerRegistrationRequest.getProviderId()));
        assertThat(containerRecord.getSubmitterRole(), is(containerRegistrationRequest.getSubmitterRole()));
        assertThat(containerRecord.getSubmitterId(), is(containerRegistrationRequest.getSubmitterId()));
    }

}
