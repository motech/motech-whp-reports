package org.motechproject.whp.reports.webservice.mapper;


import org.junit.Test;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.motechproject.whp.reports.date.WHPDate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ContainerSummaryMapperTest {

    private  ContainerSummaryMapper containerSummaryMapper = new ContainerSummaryMapper();

    @Test
    public void shouldMapContainerRecordToContainerSummary() {

        ContainerRecord containerRecord = new ContainerRecordBuilder()
                .withContainerId("containerId")
                .withIssuedOnDate(new Date())
                .withProviderId("providerId")
                .withSubmittedBy("CmfAdmin")
                .withSubmitterId("admin")
                .withProviderDistrict("Patna")
                .withRegistrationInstance("Instance")
                .withChannel("IVR")
                .withSmearTestResults(new Date(), "result1", new Date(), "result2")
                .withLabName("LabName")
                .withLabNumber("labNumber")
                .withLabResultsCapturedOn(new Date())
                .withPatientId("patient1")
                .withStatus("Close")
                .withReasonForClosure("reasonForClosureCode", "reasonForClosureText")
                .withAlternateDiagnosis("1027", "Other bacterial diseases,not elsewhere classified")
                .withDiagnosis("Positive")
                .withClosureDate(new Date())
                .withTbId("tbID")
                .withConsultationDate(new Date())
                .withMappingInstance("mappingInstance")
                .build();

        List<ContainerRecord> containerRecords = Arrays.asList(containerRecord);

        List<ContainerSummary> containerSummaryList = containerSummaryMapper.map(containerRecords);

        ContainerSummary containerSummary = containerSummaryList.get(0);

        assertEquals(containerRecord.getContainerId(), containerSummary.getContainerId());
        assertEquals(containerRecord.getIssuedOn(), containerSummary.getIssuedOn());
        assertEquals(containerRecord.getProviderId(), containerSummary.getProviderId());
        assertEquals(containerRecord.getProviderDistrict(), containerSummary.getProviderDistrict());
        assertEquals(containerRecord.getSubmitterId(), containerSummary.getRegisteredBy());
        assertEquals(containerRecord.getRegistrationInstance(), containerSummary.getRegistrationInstance());
        assertEquals(containerRecord.getChannelId(), containerSummary.getChannelId());
        assertEquals(containerRecord.getSmearTestDate1(), containerSummary.getSmearTestDate1());
        assertEquals(containerRecord.getSmearTestResult1(), containerSummary.getSmearTestResult1());
        assertEquals(containerRecord.getSmearTestDate2(), containerSummary.getSmearTestDate2());
        assertEquals(containerRecord.getSmearTestResult2(), containerSummary.getSmearTestResult2());
        assertEquals(containerRecord.getLabName(), containerSummary.getLabName());
        assertEquals(containerRecord.getLabNumber(), containerSummary.getLabNumber());
        assertEquals(containerRecord.getLabResultsCapturedOn(), containerSummary.getLabResultsCapturedOn());
        assertEquals(containerRecord.getPatientId(), containerSummary.getPatientId());
        assertEquals(containerRecord.getAlternateDiagnosisCode(), containerSummary.getAlternateDiagnosisCode());
        assertEquals(containerRecord.getClosureDate(), containerSummary.getClosureDate());
        assertEquals(containerRecord.getConsultationDate(), containerSummary.getConsultationDate());
        assertEquals(containerRecord.getAlternateDiagnosis().getText(), containerSummary.getAlternateDiagnosisName());
        assertEquals(containerRecord.getReasonForClosure().getText(), containerSummary.getReasonForClosure());
        assertEquals(containerRecord.getStatus(), containerSummary.getStatus());
        assertEquals(containerRecord.getDiagnosis(), containerSummary.getDiagnosis());
        assertEquals(containerRecord.getMappingInstance(), containerSummary.getMappingInstance());
        assertEquals(containerRecord.getTbId(), containerSummary.getTbId());
    }

    @Test
    public void shouldMapContainerRecordsWithNullValues() {

        ContainerRecord containerRecord = new ContainerRecordBuilder().build();

        List<ContainerRecord> containerRecords = Arrays.asList(containerRecord);

        List<ContainerSummary> containerSummaryList = containerSummaryMapper.map(containerRecords);

        ContainerSummary containerSummary = containerSummaryList.get(0);

        assertNull(containerSummary.getContainerId());
        assertNull(containerSummary.getIssuedOn());
        assertNull(containerSummary.getProviderId());
        assertNull(containerSummary.getRegisteredBy());
        assertNull(containerSummary.getRegistrationInstance());
        assertNull(containerSummary.getChannelId());
        assertNull(containerSummary.getSmearTestDate1());
        assertNull(containerSummary.getSmearTestResult1());
        assertNull(containerSummary.getSmearTestDate2());
        assertNull(containerSummary.getSmearTestResult2());
        assertNull(containerSummary.getLabName());
        assertNull(containerSummary.getLabNumber());
        assertNull(containerSummary.getLabResultsCapturedOn());
        assertNull(containerSummary.getPatientId());
        assertNull(containerSummary.getReasonForClosure());
        assertNull(containerSummary.getAlternateDiagnosisCode());
        assertNull(containerSummary.getClosureDate());
        assertNull(containerSummary.getConsultationDate());
        assertNull(containerSummary.getStatus());
        assertNull(containerSummary.getDiagnosis());
        assertNull(containerSummary.getMappingInstance());
        assertNull(containerSummary.getTbId());
    }
}
