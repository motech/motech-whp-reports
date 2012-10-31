package org.motechproject.whp.reports.builder;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.FlashingLog;

import java.sql.Date;
import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

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
    public void shouldCreateFlashingLog() {
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
    public void shouldCreateSputumTrackingLog() {
        java.util.Date now = new java.util.Date();

        ContainerRegistrationReportingRequest containerRegistrationReportingRequest = new ContainerRegistrationReportingRequest();
        containerRegistrationReportingRequest.setContainerId("containerId");
        containerRegistrationReportingRequest.setInstance("PreTreatment");
        containerRegistrationReportingRequest.setDateIssuedOn(now);
        containerRegistrationReportingRequest.setProviderId("raj");
        containerRegistrationReportingRequest.setSubmitterRole("CmfAdmin");
        containerRegistrationReportingRequest.setSubmitterId("submitterId");

        ContainerRecord containerRecord = DomainBuilder.buildContainerRegistrationRecord(containerRegistrationReportingRequest);

        assertThat(containerRecord.getContainerId(), is(containerRegistrationReportingRequest.getContainerId()));
        assertThat(containerRecord.getInstance(), is(containerRegistrationReportingRequest.getInstance()));
        assertThat(containerRecord.getDateIssuedOn(), is(containerRegistrationReportingRequest.getDateIssuedOn()));
        assertThat(containerRecord.getProviderId(), is(containerRegistrationReportingRequest.getProviderId()));
        assertThat(containerRecord.getSubmitterRole(), is(containerRegistrationReportingRequest.getSubmitterRole()));
        assertThat(containerRecord.getSubmitterId(), is(containerRegistrationReportingRequest.getSubmitterId()));
    }

    @Test
    public void shouldPopulateLabResultsIntoContainerRecord() {

        java.util.Date now = new java.util.Date();

        SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest = new SputumLabResultsCaptureReportingRequest();
        sputumLabResultsCaptureReportingRequest.setContainerId("containerId");
        sputumLabResultsCaptureReportingRequest.setLabName("labName");
        sputumLabResultsCaptureReportingRequest.setLabNumber("labNumber");
        sputumLabResultsCaptureReportingRequest.setCumulativeResult("cumulativeResult");
        sputumLabResultsCaptureReportingRequest.setSmearTestDate1(now);
        sputumLabResultsCaptureReportingRequest.setSmearTestDate2(now);
        sputumLabResultsCaptureReportingRequest.setSmearTestResult1("result1");
        sputumLabResultsCaptureReportingRequest.setSmearTestResult2("result2");

        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(sputumLabResultsCaptureReportingRequest.getContainerId());

        DomainBuilder.populateLabResults(sputumLabResultsCaptureReportingRequest,containerRecord);

        assertEquals(sputumLabResultsCaptureReportingRequest.getLabName(), containerRecord.getLabName());
        assertEquals(sputumLabResultsCaptureReportingRequest.getLabNumber(), containerRecord.getLabNumber());
        assertEquals(sputumLabResultsCaptureReportingRequest.getLabNumber(), containerRecord.getLabNumber());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestResult1(), containerRecord.getSmearTestResult1());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestResult2(), containerRecord.getSmearTestResult2());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestDate1(), containerRecord.getSmearTestDate1());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestDate2(), containerRecord.getSmearTestDate2());
        assertEquals(sputumLabResultsCaptureReportingRequest.getCumulativeResult(), containerRecord.getCumulativeResult());
    }

    @Test
    public void shouldUpdateContainerStatus() {
        java.util.Date now = new java.util.Date();

        ContainerStatusReportingRequest containerStatusReportingRequest = new ContainerStatusReportingRequest();
        containerStatusReportingRequest.setContainerId("containerId");
        containerStatusReportingRequest.setAlternateDiagnosisCode("alternate");
        containerStatusReportingRequest.setClosureDate(now);
        containerStatusReportingRequest.setConsultationDate(now);
        containerStatusReportingRequest.setReasonForClosure("reason");
        containerStatusReportingRequest.setContainerStatus("status");

        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(containerStatusReportingRequest.getContainerId());

        DomainBuilder.updateContainerStatus(containerStatusReportingRequest, containerRecord);

        assertEquals(containerStatusReportingRequest.getAlternateDiagnosisCode(), containerRecord.getAlternateDiagnosisCode());
        assertEquals(containerStatusReportingRequest.getClosureDate(), containerRecord.getClosureDate());
        assertEquals(containerStatusReportingRequest.getConsultationDate(), containerRecord.getConsultationDate());
        assertEquals(containerStatusReportingRequest.getContainerStatus(), containerRecord.getContainerStatus());
        assertEquals(containerStatusReportingRequest.getReasonForClosure(), containerRecord.getReasonForClosure());
    }
}
