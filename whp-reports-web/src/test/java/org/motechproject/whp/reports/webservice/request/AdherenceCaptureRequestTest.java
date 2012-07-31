package org.motechproject.whp.reports.webservice.request;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;

public class AdherenceCaptureRequestTest {

    @Test
    public void shouldCopyTimeTaken() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getTimeTaken());
        request.setTimeTaken(1000l);
        assertEquals(new Long(1000l), request.buildAdherenceSubmission().getTimeTaken());
    }

    @Test
    public void shouldCopyCallId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getCallId());
        request.setCallId("callId");
        assertEquals("callId", request.buildAdherenceSubmission().getCallId());
    }

    @Test
    public void shouldCopyStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getStatus());
        request.setStatus("status");
        assertEquals("status", request.buildAdherenceSubmission().getStatus());
    }

    @Test
    public void shouldCopyPatientId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getPatientId());
        request.setPatientId("patientId");
        assertEquals("patientId", request.buildAdherenceSubmission().getPatientId());
    }

    @Test
    public void shouldCopySubmittedValue() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getSubmittedValue());
        request.setSubmittedValue(1);
        assertEquals(new Integer(1), request.buildAdherenceSubmission().getSubmittedValue());
    }

    @Test
    public void shouldCopyProviderId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getProviderId());
        request.setProviderId("providerId");
        assertEquals("providerId", request.buildAdherenceSubmission().getProviderId());
    }

    @Test
    public void shouldCopySubmittedBy() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(request.buildAdherenceSubmission().getSubmittedBy());
        request.setProviderId("submittedBy");
        assertEquals("submittedBy", request.buildAdherenceSubmission().getProviderId());
    }

    @Test
    public void shouldCopyValidStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertFalse(request.buildAdherenceSubmission().isValid());
        request.setValid(true);
        assertEquals(true, request.buildAdherenceSubmission().isValid());
    }
}
