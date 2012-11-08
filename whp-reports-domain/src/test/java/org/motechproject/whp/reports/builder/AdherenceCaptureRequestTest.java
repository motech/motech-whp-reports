package org.motechproject.whp.reports.builder;


import org.junit.Test;
import org.motechproject.whp.reports.contract.AdherenceCaptureRequest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;

public class AdherenceCaptureRequestTest {

    private DomainMapper domainMapper = new DomainMapper();

    @Test
    public void shouldCopyTimeTaken() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getTimeTaken());
        request.setTimeTaken(1000l);
        assertEquals(new Long(1000l), domainMapper.mapAdherenceSubmission(request).getTimeTaken());
    }

    @Test
    public void shouldCopyCallId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getCallId());
        request.setCallId("callId");
        assertEquals("callId", domainMapper.mapAdherenceSubmission(request).getCallId());
    }

    @Test
    public void shouldCopyStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getStatus());
        request.setStatus("status");
        assertEquals("status", domainMapper.mapAdherenceSubmission(request).getStatus());
    }

    @Test
    public void shouldCopyPatientId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getPatientId());
        request.setPatientId("patientId");
        assertEquals("patientId", domainMapper.mapAdherenceSubmission(request).getPatientId());
    }

    @Test
    public void shouldCopySubmittedValue() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getSubmittedValue());
        request.setSubmittedValue("1");
        assertEquals("1", domainMapper.mapAdherenceSubmission(request).getSubmittedValue());
    }

    @Test
    public void shouldCopyProviderId() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getProviderId());
        request.setProviderId("providerId");
        assertEquals("providerId", domainMapper.mapAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopySubmittedBy() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertNull(domainMapper.mapAdherenceSubmission(request).getSubmittedBy());
        request.setProviderId("submittedBy");
        assertEquals("submittedBy", domainMapper.mapAdherenceSubmission(request).getProviderId());
    }

    @Test
    public void shouldCopyValidStatus() {
        AdherenceCaptureRequest request = new AdherenceCaptureRequest();

        assertFalse(domainMapper.mapAdherenceSubmission(request).isValid());
        request.setValid(true);
        assertEquals(true, domainMapper.mapAdherenceSubmission(request).isValid());
    }
}
