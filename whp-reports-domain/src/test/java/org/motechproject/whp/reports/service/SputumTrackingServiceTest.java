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
import org.motechproject.whp.reports.repository.AllSputumTrackingRecords;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class SputumTrackingServiceTest {

    @Mock
    private AllSputumTrackingRecords allSputumTrackingRecords;
    private SputumTrackingService sputumTrackingService;

    @Before()
    public void setup(){
        initMocks(this);
        sputumTrackingService = new SputumTrackingService(allSputumTrackingRecords);
    }

    @Test
    public void shouldSaveContainerRegistrationReportingRequest(){

        ContainerRegistrationReportingRequest request = new ContainerRegistrationReportingRequest();
        request.setIssuedOn(new Date());
        sputumTrackingService.recordContainerRegistration(request);

        verify(allSputumTrackingRecords).save(any(ContainerRecord.class));
    }

    @Test
    public void shouldCaptureSputumLabResults() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(allSputumTrackingRecords.getByContainerId(containerId)).thenReturn(existingContainer);

        SputumLabResultsCaptureReportingRequest request = new SputumLabResultsCaptureReportingRequest();
        request.setSmearTestDate1(new Date());
        request.setSmearTestDate2(new Date());
        request.setContainerId(containerId);
        sputumTrackingService.recordLabResults(request);

        verify(allSputumTrackingRecords).getByContainerId(containerId);
        verify(allSputumTrackingRecords).save(existingContainer);
    }

    @Test
    public void shouldUpdateContainerStatus() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(allSputumTrackingRecords.getByContainerId(containerId)).thenReturn(existingContainer);

        ContainerStatusReportingRequest request = new ContainerStatusReportingRequest();
        request.setContainerId(containerId);
        request.setClosureDate(DateTime.now());
        request.setConsultationDate(new Date());
        sputumTrackingService.updateContainerStatus(request);

        verify(allSputumTrackingRecords).getByContainerId(containerId);
        verify(allSputumTrackingRecords).save(existingContainer);

    }

    @Test
    public void shouldUpdateContainerPatientMapping() {
        ContainerRecord existingContainer = new ContainerRecord();
        String containerId = "container";
        existingContainer.setContainerId(containerId);
        when(allSputumTrackingRecords.getByContainerId(containerId)).thenReturn(existingContainer);

        ContainerPatientMappingReportingRequest request = new ContainerPatientMappingReportingRequest();
        request.setClosureDate(DateTime.now());
        request.setConsultationDate(new Date());
        request.setContainerId(containerId);
        sputumTrackingService.updateContainerPatientMapping(request);

        verify(allSputumTrackingRecords).getByContainerId(containerId);
        verify(allSputumTrackingRecords).save(existingContainer);
    }

}
