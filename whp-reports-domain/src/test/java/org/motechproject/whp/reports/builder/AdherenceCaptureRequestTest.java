package org.motechproject.whp.reports.builder;


import org.junit.Test;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;

public class AdherenceCaptureRequestTest {

    private DomainBuilder domainBuilder = new DomainBuilder();

    @Test
    public void shouldCopyTimeTaken() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getTimeTaken());
        request.setTimeTaken(1000l);
        assertEquals(new Long(1000l), domainBuilder.buildAdherenceSubmission(request).getTimeTaken());
    }

    @Test
    public void shouldCopyCallId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getCallId());
        request.setCallId("callId");
        assertEquals("callId", domainBuilder.buildAdherenceSubmission(request).getCallId());
    }

    @Test
    public void shouldCopyStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getStatus());
        request.setStatus("status");
        assertEquals("status", domainBuilder.buildAdherenceSubmission(request).getStatus());
    }

    @Test
    public void shouldCopyPatientId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getPatientId());
        request.setPatientId("patientId");
        assertEquals("patientId", domainBuilder.buildAdherenceSubmission(request).getPatientId());
    }

    @Test
    public void shouldCopySubmittedValue() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getSubmittedValue());
        request.setSubmittedValue("1");
        assertEquals("1", domainBuilder.buildAdherenceSubmission(request).getSubmittedValue());
    }

    @Test
    public void shouldCopyProviderId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getProviderId());
        request.setProviderId("providerId");
        assertEquals("providerId", domainBuilder.buildAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopySubmittedBy() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainBuilder.buildAdherenceSubmission(request).getSubmittedBy());
        request.setProviderId("submittedBy");
        assertEquals("submittedBy", domainBuilder.buildAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopyValidStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertFalse(domainBuilder.buildAdherenceSubmission(request).isValid());
        request.setValid(true);
        assertEquals(true, domainBuilder.buildAdherenceSubmission(request).isValid());
    }
}
