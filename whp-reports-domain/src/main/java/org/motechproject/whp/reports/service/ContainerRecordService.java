package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.paging.ContainerRecordPageRequest;
import org.motechproject.whp.reports.mapper.ContainerReportingRequestMapper;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ContainerRecordService {

    private ContainerRecordRepository containerRecordRepository;
    private ContainerReportingRequestMapper requestMapper;

    // Required by Spring to crete proxy as class is @Transactional
    ContainerRecordService() {}

    @Autowired
    public ContainerRecordService(ContainerRecordRepository containerRecordRepository, ContainerReportingRequestMapper requestMapper) {
        this.containerRecordRepository = containerRecordRepository;
        this.requestMapper = requestMapper;
    }

    public void recordContainerRegistration(ContainerRegistrationReportingRequest containerRegistrationReportingRequest) {
        ContainerRecord containerRecord = requestMapper.mapContainerRecord(containerRegistrationReportingRequest);
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

    public List<ContainerRecord> getAll(int pageNo, int pageSize) {
        ContainerRecordPageRequest pageRequest = new ContainerRecordPageRequest(pageNo, pageSize);
        return containerRecordRepository.findAll(pageRequest).getContent();
    }

    public void updateContainerUserGivenDetails(UserGivenPatientDetailsReportingRequest request) {
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(request.getContainerId());
        requestMapper.updateUserGivenPatientDetails(request, containerRecord);
        containerRecordRepository.save(containerRecord);
    }

    public void deleteContainerUserGivenDetails(UserGivenPatientDetailsReportingRequest request){
        ContainerRecord containerRecord = containerRecordRepository.findByContainerId(request.getContainerId());
        if(containerRecord!=null){
        requestMapper.updateUserGivenPatientDetails(request, containerRecord);
        containerRecordRepository.delete(containerRecord);
        }
    }
}
