package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.builder.DomainBuilder;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.repository.AllSputumTrackingRecords;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SputumTrackingService {

    private AllSputumTrackingRecords allSputumTrackingRecords;

    /* Required for spring proxy */
    SputumTrackingService(){
    }

    public SputumTrackingService(AllSputumTrackingRecords allSputumTrackingRecords) {
        this.allSputumTrackingRecords = allSputumTrackingRecords;
    }

    public void recordContainerRegistration(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = DomainBuilder.buildContainerRegistrationRecord(containerRegistrationReportingRequest);
        allSputumTrackingRecords.save(containerRecord);
    }

    public void recordLabResults(SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest) {
        ContainerRecord containerRecord = allSputumTrackingRecords.getByContainerId(sputumLabResultsCaptureReportingRequest.getContainerId());
        DomainBuilder.populateLabResults(sputumLabResultsCaptureReportingRequest, containerRecord);
        allSputumTrackingRecords.save(containerRecord);

    }

    public void updateContainerStatus(ContainerStatusReportingRequest containerStatusReportingRequest) {
        ContainerRecord containerRecord = allSputumTrackingRecords.getByContainerId(containerStatusReportingRequest.getContainerId());
        DomainBuilder.updateContainerStatus(containerStatusReportingRequest, containerRecord);
        allSputumTrackingRecords.save(containerRecord);
    }
}
