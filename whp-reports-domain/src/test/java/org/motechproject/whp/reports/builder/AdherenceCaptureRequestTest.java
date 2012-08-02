package org.motechproject.whp.reports.builder;


import org.junit.Test;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;

import static junit.framework.Assert.*;
import static org.motechproject.whp.reports.builder.DomainBuilder.buildAdherenceSubmission;

public class AdherenceCaptureRequestTest {

    @Test
    public void shouldCopyTimeTaken() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getTimeTaken());
        request.setTimeTaken(1000l);
        assertEquals(new Long(1000l), buildAdherenceSubmission(request).getTimeTaken());
    }

    @Test
    public void shouldCopyCallId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getCallId());
        request.setCallId("callId");
        assertEquals("callId", buildAdherenceSubmission(request).getCallId());
    }

    @Test
    public void shouldCopyStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getStatus());
        request.setStatus("status");
        assertEquals("status", buildAdherenceSubmission(request).getStatus());
    }

    @Test
    public void shouldCopyPatientId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getPatientId());
        request.setPatientId("patientId");
        assertEquals("patientId", buildAdherenceSubmission(request).getPatientId());
    }

    @Test
    public void shouldCopySubmittedValue() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getSubmittedValue());
        request.setSubmittedValue(1);
        assertEquals(new Integer(1), buildAdherenceSubmission(request).getSubmittedValue());
    }

    @Test
    public void shouldCopyProviderId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getProviderId());
        request.setProviderId("providerId");
        assertEquals("providerId", buildAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopySubmittedBy() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(buildAdherenceSubmission(request).getSubmittedBy());
        request.setProviderId("submittedBy");
        assertEquals("submittedBy", buildAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopyValidStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertFalse(buildAdherenceSubmission(request).isValid());
        request.setValid(true);
        assertEquals(true, buildAdherenceSubmission(request).isValid());
    }
}
