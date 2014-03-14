package org.motechproject.whp.reports.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.*;
import org.motechproject.whp.reports.domain.measure.container.ContainerRecord;
import org.motechproject.whp.reports.domain.paging.ContainerRecordPageRequest;
import org.motechproject.whp.reports.mapper.ContainerReportingRequestMapper;
import org.motechproject.whp.reports.repository.ContainerRecordRepository;
import org.springframework.data.domain.Page;

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
    private ContainerReportingRequestMapper requestMapper;
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
        SputumLabResultsCaptureReportingRequest request = new SputumLabResultsCaptureReportingRequest();
        request.setContainerId("container");

        when(containerRecordRepository.findByContainerId(request.getContainerId())).thenReturn(existingContainer);

        containerRecordService.recordLabResults(request);

        verify(containerRecordRepository).findByContainerId(request.getContainerId());
        verify(containerRecordRepository).save(existingContainer);
        verify(requestMapper).populateSputumLabResults(request, existingContainer);
    }

    @Test
    public void shouldUpdateContainerStatus() {
        ContainerRecord existingContainer = new ContainerRecord();
        ContainerStatusReportingRequest request = new ContainerStatusReportingRequest();
        request.setContainerId("container");

        when(containerRecordRepository.findByContainerId(request.getContainerId())).thenReturn(existingContainer);

        containerRecordService.updateContainerStatus(request);

        verify(containerRecordRepository).findByContainerId(request.getContainerId());
        verify(containerRecordRepository).save(existingContainer);
        verify(requestMapper).updateContainerStatus(request, existingContainer);
    }

    @Test
    public void shouldUpdateContainerPatientMapping() {
        ContainerRecord existingContainer = new ContainerRecord();
        ContainerPatientMappingReportingRequest request = new ContainerPatientMappingReportingRequest();
        request.setContainerId("container");

        when(containerRecordRepository.findByContainerId(request.getContainerId())).thenReturn(existingContainer);

        containerRecordService.updateContainerPatientMapping(request);

        verify(containerRecordRepository).findByContainerId(request.getContainerId());
        verify(containerRecordRepository).save(existingContainer);
        verify(requestMapper).updateContainerPatientMapping(request, existingContainer);
    }

    @Test
    public void shouldUpdateContainerUserGivenDetailsMapping() {
        ContainerRecord existingContainer = new ContainerRecord();
        UserGivenPatientDetailsReportingRequest request = new UserGivenPatientDetailsReportingRequest();
        request.setContainerId("container");

        when(containerRecordRepository.findByContainerId(request.getContainerId())).thenReturn(existingContainer);

        containerRecordService.updateContainerUserGivenDetails(request);

        verify(containerRecordRepository).findByContainerId(request.getContainerId());
        verify(containerRecordRepository).save(existingContainer);
        verify(requestMapper).updateUserGivenPatientDetails(request, existingContainer);
    }
    
    @Test
    public void shouldPageContainerRecords() {
        List containerRecordList1 = mock(List.class);
        List containerRecordList2 = mock(List.class);

        Page<ContainerRecord> page1 = mock(Page.class);
        when(page1.getContent()).thenReturn(containerRecordList1);

        Page<ContainerRecord> page2 = mock(Page.class);
        when(page2.getContent()).thenReturn(containerRecordList2);

        ContainerRecordPageRequest pageRequest1 = new ContainerRecordPageRequest(1, 3);
        ContainerRecordPageRequest pageRequest2 = new ContainerRecordPageRequest(2, 3);

        when(containerRecordRepository.findAll(pageRequest1)).thenReturn(page1);
        when(containerRecordRepository.findAll(pageRequest2)).thenReturn(page2);

        assertEquals(containerRecordList1, containerRecordService.getAll(1, 3));
        assertEquals(containerRecordList2, containerRecordService.getAll(2, 3));

        verify(containerRecordRepository).findAll(pageRequest1);
        verify(containerRecordRepository).findAll(pageRequest2);
        verify(page1).getContent();
        verify(page2).getContent();
    }

    @Test
    public void shouldDeleteContainer() {
        ContainerRecord existingContainer = new ContainerRecord();
        existingContainer.setContainerId("container");
        UserGivenPatientDetailsReportingRequest userGivenPatientDetailsReportingRequest = new UserGivenPatientDetailsReportingRequest();
        userGivenPatientDetailsReportingRequest.setContainerId(existingContainer.getContainerId());

        when(containerRecordRepository.findByContainerId(userGivenPatientDetailsReportingRequest.getContainerId())).thenReturn(existingContainer);

        containerRecordService.deleteContainerUserGivenDetails(userGivenPatientDetailsReportingRequest);

        verify(containerRecordRepository).findByContainerId(userGivenPatientDetailsReportingRequest.getContainerId());
        verify(containerRecordRepository).delete(existingContainer);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(containerRecordRepository);
    }
}

