package org.motechproject.whp.reports.webservice.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.service.ProviderReminderCallLogService;
import org.motechproject.whp.reports.webservice.mapper.ProviderReminderCallLogSummaryMapper;
import org.motechproject.whp.reports.webservice.model.ProviderReminderCallLogSummary;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderReminderCallLogReportServiceTest {

    @Mock
    ProviderReminderCallLogService providerReminderCallLogService;
    @Mock
    ProviderReminderCallLogSummaryMapper providerReminderCallLogSummaryMapper;

    ProviderReminderCallLogReportService providerReminderCallLogReportService;

    @Before
    public void setUp() {
        initMocks(this);
        providerReminderCallLogReportService = new ProviderReminderCallLogReportService(providerReminderCallLogService, providerReminderCallLogSummaryMapper);
    }

    @Test
    public void shouldReturnProviderReminderSummaryList() {
        List providerReminderCallLogs = mock(List.class);
        int pageNumber = 1;
        when(providerReminderCallLogService.getAll(pageNumber, ProviderReminderCallLogReportService.PAGE_SIZE)).thenReturn(providerReminderCallLogs);

        List expectedSummaryList = mock(List.class);
        when(providerReminderCallLogSummaryMapper.map(providerReminderCallLogs)).thenReturn(expectedSummaryList);

        List<ProviderReminderCallLogSummary> summaryList = providerReminderCallLogReportService.summary(pageNumber);

        assertEquals(expectedSummaryList, summaryList);
    }
}
