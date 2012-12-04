package org.motechproject.whp.reports.webservice.mapper;

import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.motechproject.whp.reports.webservice.util.WHPDate.nullSafeDate;
import static org.motechproject.whp.reports.webservice.util.WHPDate.nullSafeTimestamp;

@Component
public class ContainerSummaryMapper {

    public List<ContainerSummary> map(List<ContainerRecord> containerRecords) {
        List<ContainerSummary> containerSummaryList = new ArrayList<>();
        for (ContainerRecord containerRecord : containerRecords) {
            containerSummaryList.add(map(containerRecord));
        }
        return containerSummaryList;
    }

    private ContainerSummary map(ContainerRecord containerRecord) {
        ContainerSummary containerSummary = new ContainerSummary();

        containerSummary.setContainerId(containerRecord.getContainerId());
        containerSummary.setProviderId(containerRecord.getProviderId());
        containerSummary.setProviderDistrict(containerRecord.getProviderDistrict());
        containerSummary.setIssuedOn(nullSafeDate(containerRecord.getIssuedOn()));
        containerSummary.setSubmitterId(containerRecord.getSubmitterId());
        containerSummary.setRegistrationInstance(containerRecord.getRegistrationInstance());
        containerSummary.setChannelId(containerRecord.getChannelId());
        containerSummary.setSmearTestDate1(nullSafeDate(containerRecord.getSmearTestDate1()));
        containerSummary.setSmearTestResult1(containerRecord.getSmearTestResult1());
        containerSummary.setSmearTestDate2(nullSafeDate(containerRecord.getSmearTestDate2()));
        containerSummary.setSmearTestResult2(containerRecord.getSmearTestResult2());
        containerSummary.setLabName(containerRecord.getLabName());
        containerSummary.setLabNumber(containerRecord.getLabNumber());
        containerSummary.setLabResultsCapturedOn(nullSafeTimestamp(containerRecord.getLabResultsCapturedOn()));
        containerSummary.setPatientId(containerRecord.getPatientId());
        containerSummary.setAlternateDiagnosisCode(containerRecord.getAlternateDiagnosisCode());
        containerSummary.setClosureDate(nullSafeTimestamp(containerRecord.getClosureDate()));
        containerSummary.setConsultationDate(nullSafeDate(containerRecord.getConsultationDate()));

        if (containerRecord.getAlternateDiagnosis() != null) {
            containerSummary.setAlternateDiagnosisName(containerRecord.getAlternateDiagnosis().getText());
            containerSummary.setReasonForClosure(containerRecord.getReasonForClosure().getText());
        }

        return containerSummary;
    }
}
