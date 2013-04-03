package org.motechproject.whp.reports.mapper;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails;

import java.sql.Timestamp;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.motechproject.whp.reports.contract.builder.ContainerRegistrationRequestBuilder.defaultRequest;

public class ContainerReportingRequestMapperTest {

    private ContainerReportingRequestMapper requestMapper = new ContainerReportingRequestMapper();

    @Test
    public void shouldCreateContainerRecordOnRegistration() {
        ContainerRegistrationReportingRequest containerRegistrationReportingRequest = defaultRequest();

        ContainerRecord containerRecord = requestMapper.mapContainerRecord(containerRegistrationReportingRequest);

        assertThat(containerRecord.getContainerId(), is(containerRegistrationReportingRequest.getContainerId()));
        assertThat(containerRecord.getRegistrationInstance(), is(containerRegistrationReportingRequest.getInstance()));
        assertThat(containerRecord.getIssuedOn(), is(containerRegistrationReportingRequest.getIssuedOn()));
        assertThat(containerRecord.getProviderId(), is(containerRegistrationReportingRequest.getProviderId()));
        assertThat(containerRecord.getSubmitterRole(), is(containerRegistrationReportingRequest.getSubmitterRole()));
        assertThat(containerRecord.getSubmitterId(), is(containerRegistrationReportingRequest.getSubmitterId()));

        assertUserGivenPatientDetails(containerRegistrationReportingRequest.getUserGivenPatientDetailsRequest(), containerRecord.getUserGivenPatientDetails());
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
        assertEquals(containerStatusReportingRequest.getReasonForClosure(), containerRecord.getReasonForClosureCode());
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
        assertEquals(containerPatientMappingReportingRequest.getReasonForClosure(), containerRecord.getReasonForClosureCode());
        assertEquals(containerPatientMappingReportingRequest.getStatus(), containerRecord.getStatus());
        assertEquals(containerPatientMappingReportingRequest.getTbId(), containerRecord.getTbId());
        assertEquals(containerPatientMappingReportingRequest.getDiagnosis(), containerRecord.getDiagnosis());
    }

    @Test
    public void shouldUpdateUserGivenPatientDetails() {
        UserGivenPatientDetailsReportingRequest userGivenPatientDetailsReportingRequest = new UserGivenPatientDetailsReportingRequest();
        userGivenPatientDetailsReportingRequest.setContainerId("containerId");
        userGivenPatientDetailsReportingRequest.setPatientId("patientId");
        userGivenPatientDetailsReportingRequest.setPatientName("patientName");
        userGivenPatientDetailsReportingRequest.setPatientAge(45);
        userGivenPatientDetailsReportingRequest.setGender("M");

        ContainerRecord containerRecord = new ContainerRecord();
        containerRecord.setContainerId(userGivenPatientDetailsReportingRequest.getContainerId());

        requestMapper.updateUserGivenPatientDetails(userGivenPatientDetailsReportingRequest, containerRecord);

        assertEquals(userGivenPatientDetailsReportingRequest.getContainerId(), containerRecord.getContainerId());
        UserGivenPatientDetails userGivenPatientDetails = containerRecord.getUserGivenPatientDetails();
        assertEquals(userGivenPatientDetailsReportingRequest.getPatientId(), userGivenPatientDetails.getPatientId());
        assertEquals(userGivenPatientDetailsReportingRequest.getPatientName(), userGivenPatientDetails.getPatientName());
        assertEquals(userGivenPatientDetailsReportingRequest.getGender(), userGivenPatientDetails.getGender());
        assertEquals(userGivenPatientDetailsReportingRequest.getPatientAge(), userGivenPatientDetails.getPatientAge());
    }

    private void assertUserGivenPatientDetails(UserGivenPatientDetailsRequest expectedPatientDetails, org.motechproject.whp.reports.domain.measure.container.UserGivenPatientDetails actualPatientDetails) {
        assertEquals(expectedPatientDetails.getPatientName(), actualPatientDetails.getPatientName());
        assertEquals(expectedPatientDetails.getPatientId(), actualPatientDetails.getPatientId());
        assertEquals(expectedPatientDetails.getPatientAge(), actualPatientDetails.getPatientAge());
        assertEquals(expectedPatientDetails.getGender(), actualPatientDetails.getGender());
    }


}
