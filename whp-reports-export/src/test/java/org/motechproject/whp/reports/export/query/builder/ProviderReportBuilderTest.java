package org.motechproject.whp.reports.export.query.builder;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;
import org.motechproject.whp.reports.export.query.model.ProviderReportRequest;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

public class ProviderReportBuilderTest {
    ProviderReportBuilder providerReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private WhpExcelReportBuilder whpExcelReportBuilder;

    @Mock
    private OutputStream outputStream;
    private String fromDate;
    private String toDate;
    private String providerDistrict;
    ProviderReportRequest providerReportRequest;
    List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries;

    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        providerReportBuilder = new ProviderReportBuilder(reportQueryService, whpExcelReportBuilder);
        providerReminderCallLogSummaries = asList(mock(ProviderReminderCallLogSummary.class));
        fromDate = "20/10/2012";
        toDate = "30/11/2012";
        providerDistrict = "d1";

        providerReportRequest = new ProviderReportRequest();
        providerReportRequest.setDistrict(providerDistrict);
        providerReportRequest.setFrom(fromDate);
        providerReportRequest.setTo(toDate);
        expectedParams = new HashMap();
        expectedParams.put(ProviderReportBuilder.TEMPLATE_RESULT_KEY, providerReminderCallLogSummaries);
        expectedParams.put(ProviderReportBuilder.FROM_DATE, fromDate);
        expectedParams.put(ProviderReportBuilder.TO_DATE, toDate);
        expectedParams.put(ProviderReportBuilder.PROVIDER_DISTRICT, providerDistrict);
        expectedParams.put(ProviderReportBuilder.TOTAL_ROWS, 1);
    }


    @Test
    public void shouldCreateProviderReminderCallLogReport() {
        when(reportQueryService.getProviderReminderCallLogSummaries(providerReportRequest)).thenReturn(providerReminderCallLogSummaries);
       
        providerReportBuilder.buildProviderReminderCallLogReport(providerReportRequest, outputStream);

        verify(reportQueryService).getProviderReminderCallLogSummaries(providerReportRequest);
      }

}
