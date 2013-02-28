package org.motechproject.whp.reports.export.query.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceAuditLogReportBuilderTest extends BaseUnitTest{

    private AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ExcelReportBuilder excelReportBuilder;
    @Mock
    private OutputStream outputStream;


    private List<AdherenceAuditLogSummary> adherenceAuditLogSummaries;
    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceAuditLogReportBuilder = new AdherenceAuditLogReportBuilder(reportQueryService, excelReportBuilder);

        adherenceAuditLogSummaries = asList(new AdherenceAuditLogSummary());

        expectedParams = new HashMap();
        expectedParams.put("auditLogs", adherenceAuditLogSummaries);
    }

    @Test
    public void shouldBuildAdherenceAuditLogReport() throws IOException {
        when(reportQueryService.getAdherenceAuditLogSummaries()).thenReturn(adherenceAuditLogSummaries);

        adherenceAuditLogReportBuilder.build(outputStream);

        verify(reportQueryService).getAdherenceAuditLogSummaries();
        verify(excelReportBuilder).build(outputStream, expectedParams, AdherenceAuditLogReportBuilder.ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME);
    }

    @Test
    public void shouldReturnAdherenceAuditLogReportName() {
        assertEquals("adherenceAuditLogReport", adherenceAuditLogReportBuilder.getReportName());
    }
}
