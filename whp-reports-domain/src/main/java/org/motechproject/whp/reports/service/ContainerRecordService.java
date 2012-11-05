package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.mapper.SputumTrackingRequestMapper;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContainerRecordService {

    private ContainerRecordRepository containerRecordRepository;

    /* Required for spring proxy */
    ContainerRecordService() {
    }

    public ContainerRecordService(ContainerRecordRepository containerRecordRepository) {
        this.containerRecordRepository = containerRecordRepository;
    }

    public void recordContainerRegistration(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = SputumTrackingRequestMapper.buildContainerRegistrationRecord(containerRegistrationReportingRequest);
        containerRecordRepository.save(containerRecord);
    }

    public void recordLabResults(SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(sputumLabResultsCaptureReportingRequest.getContainerId());
        SputumTrackingRequestMapper.populateSputumLabResults(sputumLabResultsCaptureReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);

    }

    public void updateContainerStatus(ContainerStatusReportingRequest containerStatusReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(containerStatusReportingRequest.getContainerId());
        SputumTrackingRequestMapper.updateContainerStatus(containerStatusReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);
    }

    public void updateContainerPatientMapping(ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(containerPatientMappingReportingRequest.getContainerId());
        SputumTrackingRequestMapper.updateContainerPatientMapping(containerPatientMappingReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);
    }
}
