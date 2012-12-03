package org.motechproject.whp.reports.webservice.mapper;


import org.junit.Test;
import org.motechproject.whp.reports.builder.ContainerRecordBuilder;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.motechproject.whp.reports.webservice.util.WHPDate;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

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
                .withLocationId("Patna")
                .withRegistrationInstance("Instance")
                .withChannel("IVR")
                .withSmearTestResults(new Date(),"result1", new Date(), "result2")
                .withLabName("LabName")
                .withLabNumber("labNumber")
                .withLabResultsCapturedOn(new Date())
                .withPatientId("patient1")
                .withStatus("Close")
                .withReasonForClosure("reasonForClosure")
                .withAlternateDiagnosisCode("666")
                .withDiagnosis("Positive")
                .withClosureDate(new Date())
                .withTbId("tbID")
                .withConsultationDate(new Date())
                .withMappingInstance("mappingInstance")
                .build();

        ContainerSummary containerSummary = containerSummaryMapper.map(containerRecord);

        assertEquals(containerRecord.getContainerId(), containerSummary.getContainerId());
        assertEquals(WHPDate.date(containerRecord.getIssuedOn()).value(), containerSummary.getIssuedOn());
        assertEquals(containerRecord.getProviderId(), containerSummary.getProviderId());
        assertEquals(containerRecord.getSubmitterId(), containerSummary.getRegisteredBy());
        assertEquals(containerRecord.getRegistrationInstance(), containerSummary.getRegistrationInstance());
        assertEquals(containerRecord.getChannelId(), containerSummary.getChannelId());
        assertEquals(WHPDate.date(containerRecord.getSmearTestDate1()).value(), containerSummary.getSmearTestDate1());
        assertEquals(containerRecord.getSmearTestResult1(), containerSummary.getSmearTestResult1());
        assertEquals(WHPDate.date(containerRecord.getSmearTestDate2()).value(), containerSummary.getSmearTestDate2());
        assertEquals(containerRecord.getSmearTestResult2(), containerSummary.getSmearTestResult2());
        assertEquals(containerRecord.getLabName(), containerSummary.getLabName());
        assertEquals(containerRecord.getLabNumber(), containerSummary.getLabNumber());
        assertEquals(WHPDate.date(containerRecord.getLabResultsCapturedOn()).value(), containerSummary.getLabResultsCapturedOn());
        assertEquals(containerRecord.getPatientId(), containerSummary.getPatientId());
        assertEquals(containerRecord.getReasonForClosure(), containerSummary.getReasonForClosure());
        assertEquals(containerRecord.getAlternateDiagnosisCode(), containerSummary.getAlternateDiagnosisCode());
        assertEquals(WHPDate.date(containerRecord.getClosureDate()).value(), containerSummary.getClosureDate());
        assertEquals(WHPDate.date(containerRecord.getConsultationDate()).value(), containerSummary.getConsultationDate());
    }
}
