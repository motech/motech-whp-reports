package org.motechproject.whp.reports.service;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerRecordServiceTest {

    @Mock
    private ContainerRecordRepository containerRecordRepository;
    private ContainerRecordService containerRecordService;

    @Before()
    public void setup(){
        initMocks(this);
        containerRecordService = new ContainerRecordService(containerRecordRepository);
    }

    @Test
    public void shouldSaveContainerRegistrationReportingRequest(){

        ContainerRegistrationReportingRequest request = new ContainerRegistrationReportingRequest();
        request.setIssuedOn(new Date());
        containerRecordService.recordContainerRegistration(request);

        verify(containerRecordRepository).save(any(ContainerRecord.class));
    }

    @Test
    public void shouldCaptureSputumLabResults() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(containerRecordRepository.findByContainerId(containerId)).thenReturn(existingContainer);

        SputumLabResultsCaptureReportingRequest request = new SputumLabResultsCaptureReportingRequest();
        request.setSmearTestDate1(new Date());
        request.setSmearTestDate2(new Date());
        request.setContainerId(containerId);
        containerRecordService.recordLabResults(request);

        verify(containerRecordRepository).findByContainerId(containerId);
        verify(containerRecordRepository).save(existingContainer);
    }

    @Test
    public void shouldUpdateContainerStatus() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(containerRecordRepository.findByContainerId(containerId)).thenReturn(existingContainer);

        ContainerStatusReportingRequest request = new ContainerStatusReportingRequest();
        request.setContainerId(containerId);
        request.setClosureDate(DateTime.now());
        request.setConsultationDate(new Date());
        containerRecordService.updateContainerStatus(request);

        verify(containerRecordRepository).findByContainerId(containerId);
        verify(containerRecordRepository).save(existingContainer);

    }

    @Test
    public void shouldUpdateContainerPatientMapping() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(containerRecordRepository.findByContainerId(containerId)).thenReturn(existingContainer);

        ContainerPatientMappingReportingRequest request = new ContainerPatientMappingReportingRequest();
        request.setClosureDate(DateTime.now());
        request.setConsultationDate(new Date());
        request.setContainerId(containerId);
        containerRecordService.updateContainerPatientMapping(request);

        verify(containerRecordRepository).findByContainerId(containerId);
        verify(containerRecordRepository).save(existingContainer);
    }

}
