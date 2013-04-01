package org.motechproject.whp.reports.export.query.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderReminderCallLogReportBuilderTest {
    ProviderReminderCallLogReportBuilder providerReminderCallLogReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private WhpExcelReportBuilder whpExcelReportBuilder;

    @Mock
    private OutputStream outputStream;
    List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries;

    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        providerReminderCallLogReportBuilder = new ProviderReminderCallLogReportBuilder(reportQueryService, whpExcelReportBuilder);
        providerReminderCallLogSummaries = asList(mock(ProviderReminderCallLogSummary.class));

        expectedParams = new HashMap();
        expectedParams.put("callLogs", providerReminderCallLogSummaries);
    }


    @Test
    public void shouldCreateProviderReminderCallLogReport() {
        when(reportQueryService.getProviderReminderCallLogSummaries()).thenReturn(providerReminderCallLogSummaries);

        providerReminderCallLogReportBuilder.build(outputStream);

        verify(reportQueryService).getProviderReminderCallLogSummaries();
        verify(whpExcelReportBuilder).build(outputStream, expectedParams, ProviderReminderCallLogReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME);
    }

    @Test
    public void shouldReturnProviderReminderCallLogReportName() {
        assertEquals("providerReminderCallLogReport", providerReminderCallLogReportBuilder.getReportName());
    }

}
