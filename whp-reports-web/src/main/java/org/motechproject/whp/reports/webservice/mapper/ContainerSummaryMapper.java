package org.motechproject.whp.reports.webservice.mapper;

import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.motechproject.whp.reports.webservice.util.WHPDate;

public class ContainerSummaryMapper {
    public ContainerSummary map(ContainerRecord containerRecord) {
        ContainerSummary containerSummary = new ContainerSummary();

        containerSummary.setContainerId(containerRecord.getContainerId());
        containerSummary.setProviderId(containerRecord.getProviderId());
        containerSummary.setIssuedOn(WHPDate.date(containerRecord.getIssuedOn()).value());
        containerSummary.setSubmitterId(containerRecord.getSubmitterId());
        containerSummary.setRegistrationInstance(containerRecord.getRegistrationInstance());
        containerSummary.setChannelId(containerRecord.getChannelId());
        containerSummary.setSmearTestDate1(WHPDate.date(containerRecord.getSmearTestDate1()).value());
        containerSummary.setSmearTestResult1(containerRecord.getSmearTestResult1());
        containerSummary.setSmearTestDate2(WHPDate.date(containerRecord.getSmearTestDate2()).value());
        containerSummary.setSmearTestResult2(containerRecord.getSmearTestResult2());
        containerSummary.setLabName(containerRecord.getLabName());
        containerSummary.setLabNumber(containerRecord.getLabNumber());
        containerSummary.setLabResultsCapturedOn(WHPDate.date(containerRecord.getLabResultsCapturedOn()).value());
        containerSummary.setPatientId(containerRecord.getPatientId());
        containerSummary.setReasonForClosure(containerRecord.getReasonForClosure());
        containerSummary.setAlternateDiagnosisCode(containerRecord.getAlternateDiagnosisCode());
        containerSummary.setClosureDate(WHPDate.date(containerRecord.getClosureDate()).value());
        containerSummary.setConsultationDate(WHPDate.date(containerRecord.getConsultationDate()).value());

        return containerSummary;
    }
}
