package org.motechproject.whp.reports.service;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.ContainerPatientMappingReportingRequest;
import org.motechproject.whp.reports.contract.ContainerRegistrationReportingRequest;
import org.motechproject.whp.reports.contract.ContainerStatusReportingRequest;
import org.motechproject.whp.reports.contract.SputumLabResultsCaptureReportingRequest;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.domain.paging.ContainerRecordPageable;
import org.motechproject.whp.reports.mapper.ContainerTrackingReportingRequestMapper;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerRecordServiceTest {

    @Mock
    private ContainerRecordRepository containerRecordRepository;
    @Mock
    private ContainerTrackingReportingRequestMapper requestMapper;
    private ContainerRecordService containerRecordService;

    @Before()
    public void setup(){
        initMocks(this);
        containerRecordService = new ContainerRecordService(containerRecordRepository, requestMapper);
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
    
    @Test
    public void shouldPageContainerRecords() {
        List containerRecordList1 = mock(List.class);
        List containerRecordList2 = mock(List.class);

        Page<ContainerRecord> page1 = mock(Page.class);
        when(page1.getContent()).thenReturn(containerRecordList1);

        Page<ContainerRecord> page2 = mock(Page.class);
        when(page2.getContent()).thenReturn(containerRecordList2);

        ContainerRecordPageable pageRequest1 = new ContainerRecordPageable(1, 3);
        ContainerRecordPageable pageRequest2 = new ContainerRecordPageable(2, 3);

        when(containerRecordRepository.findAll(pageRequest1)).thenReturn(page1);
        when(containerRecordRepository.findAll(pageRequest2)).thenReturn(page2);

        assertEquals(containerRecordList1, containerRecordService.getAll(1, 3));
        assertEquals(containerRecordList2, containerRecordService.getAll(2, 3));

        verify(containerRecordRepository).findAll(pageRequest1);
        verify(containerRecordRepository).findAll(pageRequest2);
        verify(page1).getContent();
        verify(page2).getContent();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(containerRecordRepository);
    }
}

