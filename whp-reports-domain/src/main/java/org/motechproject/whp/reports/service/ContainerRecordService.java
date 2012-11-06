package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.mapper.ContainerTrackingReportingRequestMapper;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContainerRecordService {

    private ContainerRecordRepository containerRecordRepository;
    private ContainerTrackingReportingRequestMapper requestMapper;

    // Required by Spring to crete proxy as class is @Transactional
    ContainerRecordService() {}

    @Autowired
    public ContainerRecordService(ContainerRecordRepository containerRecordRepository, ContainerTrackingReportingRequestMapper requestMapper) {
        this.containerRecordRepository = containerRecordRepository;
        this.requestMapper = requestMapper;
    }

    public void recordContainerRegistration(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = requestMapper.buildContainerRegistrationRecord(containerRegistrationReportingRequest);
        containerRecordRepository.save(containerRecord);
    }

    public void recordLabResults(SputumLabResultsCaptureReportingRequest sputumLabResultsCaptureReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(sputumLabResultsCaptureReportingRequest.getContainerId());
        requestMapper.populateSputumLabResults(sputumLabResultsCaptureReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);

    }

    public void updateContainerStatus(ContainerStatusReportingRequest containerStatusReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(containerStatusReportingRequest.getContainerId());
        requestMapper.updateContainerStatus(containerStatusReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);
    }

    public void updateContainerPatientMapping(ContainerPatientMappingReportingRequest containerPatientMappingReportingRequest) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(containerPatientMappingReportingRequest.getContainerId());
        requestMapper.updateContainerPatientMapping(containerPatientMappingReportingRequest, containerRecord);
        containerRecordRepository.save(containerRecord);
    }
}
