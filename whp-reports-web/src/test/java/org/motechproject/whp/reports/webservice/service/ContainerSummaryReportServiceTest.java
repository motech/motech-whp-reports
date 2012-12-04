package org.motechproject.whp.reports.webservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.ContainerRecord;
import org.motechproject.whp.reports.service.ContainerRecordService;
import org.motechproject.whp.reports.webservice.mapper.ContainerSummaryMapper;
import org.motechproject.whp.reports.webservice.model.ContainerSummary;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerSummaryReportServiceTest {

    private ContainerSummaryReportService containerSummaryReportService;
    @Mock
    private ContainerRecordService containerRecordService;
    @Mock
    private ContainerSummaryMapper containerSummaryMapper;

    @Before
    public void setUp() {
        initMocks(this);
        containerSummaryReportService = new ContainerSummaryReportService(containerRecordService, containerSummaryMapper);
    }

    @Test
    public void shouldReturnContainerSummary() {
        int pageNumber = 1;

        List<ContainerRecord> containerRecords = new ArrayList<>();
        List<ContainerSummary> expectedContainerSummaries = new ArrayList<>();

        int pageSize = 10000;
        when(containerRecordService.getAll(1, pageSize)).thenReturn(containerRecords);
        when(containerSummaryMapper.map(containerRecords)).thenReturn(expectedContainerSummaries);

        List<ContainerSummary> containerSummaries = containerSummaryReportService.containerSummary(pageNumber);

        assertEquals(expectedContainerSummaries, containerSummaries);

        verify(containerRecordService).getAll(1, pageSize);
        verify(containerSummaryMapper).map(containerRecords);
    }
}
