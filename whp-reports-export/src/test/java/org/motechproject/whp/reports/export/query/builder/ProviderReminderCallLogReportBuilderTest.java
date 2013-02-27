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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderReminderCallLogReportBuilderTest {
    ProviderReminderCallLogReportBuilder providerReminderCallLogReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ReportBuilder reportBuilder;

    @Mock
    private OutputStream outputStream;
    List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries;

    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        providerReminderCallLogReportBuilder = new ProviderReminderCallLogReportBuilder(reportQueryService, reportBuilder);
        providerReminderCallLogSummaries = asList(mock(ProviderReminderCallLogSummary.class));

        expectedParams = new HashMap();
        expectedParams.put("callLogs", providerReminderCallLogSummaries);
    }


    @Test
    public void shouldCreateProviderReminderCallLogReport() {
        when(reportQueryService.getProviderReminderCallLogSummaries()).thenReturn(providerReminderCallLogSummaries);

        providerReminderCallLogReportBuilder.buildProviderReminderCallLogReport(outputStream);

        verify(reportQueryService).getProviderReminderCallLogSummaries();
        verify(reportBuilder).build(outputStream, expectedParams, ProviderReminderCallLogReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME);
    }

}
