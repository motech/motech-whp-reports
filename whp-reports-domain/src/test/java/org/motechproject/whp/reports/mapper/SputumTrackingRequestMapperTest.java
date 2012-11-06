package org.motechproject.whp.reports.mapper;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class SputumTrackingRequestMapperTest {

    private ContainerTrackingReportingRequestMapper requestMapper = new ContainerTrackingReportingRequestMapper();

    @Test
    public void shouldCreateContainerRecordOnRegistration() {
        java.util.Date now = new java.util.Date();

        ContainerRegistrationReportingRequest containerRegistrationReportingRequest = new ContainerRegistrationReportingRequest();
        containerRegistrationReportingRequest.setContainerId("containerId");
        containerRegistrationReportingRequest.setInstance("PreTreatment");
        containerRegistrationReportingRequest.setIssuedOn(now);
        containerRegistrationReportingRequest.setProviderId("raj");
        containerRegistrationReportingRequest.setSubmitterRole("CmfAdmin");
        containerRegistrationReportingRequest.setSubmitterId("submitterId");

        ContainerRecord containerRecord = requestMapper.buildContainerRegistrationRecord(containerRegistrationReportingRequest);

        assertThat(containerRecord.getContainerId(), is(containerRegistrationReportingRequest.getContainerId()));
        assertThat(containerRecord.getRegistrationInstance(), is(containerRegistrationReportingRequest.getInstance()));
        assertThat(containerRecord.getIssuedOn(), is(containerRegistrationReportingRequest.getIssuedOn()));
        assertThat(containerRecord.getProviderId(), is(containerRegistrationReportingRequest.getProviderId()));
        assertThat(containerRecord.getSubmitterRole(), is(containerRegistrationReportingRequest.getSubmitterRole()));
        assertThat(containerRecord.getSubmitterId(), is(containerRegistrationReportingRequest.getSubmitterId()));
    }

    @Test
    public void shouldPopulateContainerRecordWithSputumLabResults() {

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

        requestMapper.populateSputumLabResults(sputumLabResultsCaptureReportingRequest, containerRecord);

        assertEquals(sputumLabResultsCaptureReportingRequest.getLabName(), containerRecord.getLabName());
        assertEquals(sputumLabResultsCaptureReportingRequest.getLabNumber(), containerRecord.getLabNumber());
        assertEquals(sputumLabResultsCaptureReportingRequest.getLabNumber(), containerRecord.getLabNumber());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestResult1(), containerRecord.getSmearTestResult1());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestResult2(), containerRecord.getSmearTestResult2());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestDate1(), containerRecord.getSmearTestDate1());
        assertEquals(sputumLabResultsCaptureReportingRequest.getSmearTestDate2(), containerRecord.getSmearTestDate2());
        assertEquals(sputumLabResultsCaptureReportingRequest.getCumulativeResult(), containerRecord.getCumulativeResult());
        assertEquals(sputumLabResultsCaptureReportingRequest.getLabResultsCapturedOn(), containerRecord.getLabResultsCapturedOn());
    }

    @Test
    public void shouldUpdateContainerStatus() {
        java.util.Date now = new java.util.Date();

        ContainerStatusReportingRequest containerStatusReportingRequest = new ContainerStatusReportingRequest();
        containerStatusReportingRequest.setContainerId("containerId");
        containerStatusReportingRequest.setAlternateDiagnosisCode("alternate");
        containerStatusReportingRequest.setClosureDate(DateTime.now());
        containerStatusReportingRequest.setConsultationDate(now);
        containerStatusReportingRequest.setReasonForClosure("reason");
        containerStatusReportingRequest.setStatus("status");
        containerStatusReportingRequest.setDiagnosis("negative");

        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(containerStatusReportingRequest.getContainerId());

        requestMapper.updateContainerStatus(containerStatusReportingRequest, containerRecord);

        assertEquals(containerStatusReportingRequest.getAlternateDiagnosisCode(), containerRecord.getAlternateDiagnosisCode());
        assertEquals(new Timestamp(containerStatusReportingRequest.getClosureDate().getMillis()), containerRecord.getClosureDate());
        assertEquals(containerStatusReportingRequest.getConsultationDate(), containerRecord.getConsultationDate());
        assertEquals(containerStatusReportingRequest.getStatus(), containerRecord.getStatus());
        assertEquals(containerStatusReportingRequest.getReasonForClosure(), containerRecord.getReasonForClosure());
        assertEquals(containerStatusReportingRequest.getDiagnosis(), containerRecord.getDiagnosis());
    }

    @Test
    public void shouldUpdateContainerPatientMapping() {
        java.util.Date now = new java.util.Date();
        ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest = new ContainerPatientMappingReportingRequest();
        containerPatientMappingReportingRequest.setContainerId("container");
        containerPatientMappingReportingRequest.setClosureDate(DateTime.now());
        containerPatientMappingReportingRequest.setConsultationDate(now);
        containerPatientMappingReportingRequest.setMappingInstance("instance");
        containerPatientMappingReportingRequest.setPatientId("patient");
        containerPatientMappingReportingRequest.setReasonForClosure("reason");
        containerPatientMappingReportingRequest.setStatus("status");
        containerPatientMappingReportingRequest.setTbId("tbid");
        containerPatientMappingReportingRequest.setDiagnosis("positive");

        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(containerPatientMappingReportingRequest.getContainerId());

        requestMapper.updateContainerPatientMapping(containerPatientMappingReportingRequest, containerRecord);

        assertEquals(new Timestamp(containerPatientMappingReportingRequest.getClosureDate().getMillis()), containerRecord.getClosureDate());
        assertEquals(containerPatientMappingReportingRequest.getConsultationDate(), containerRecord.getConsultationDate());
        assertEquals(containerPatientMappingReportingRequest.getMappingInstance(), containerRecord.getMappingInstance());
        assertEquals(containerPatientMappingReportingRequest.getPatientId(), containerRecord.getPatientId());
        assertEquals(containerPatientMappingReportingRequest.getReasonForClosure(), containerRecord.getReasonForClosure());
        assertEquals(containerPatientMappingReportingRequest.getStatus(), containerRecord.getStatus());
        assertEquals(containerPatientMappingReportingRequest.getTbId(), containerRecord.getTbId());
        assertEquals(containerPatientMappingReportingRequest.getDiagnosis(), containerRecord.getDiagnosis());
    }
}
