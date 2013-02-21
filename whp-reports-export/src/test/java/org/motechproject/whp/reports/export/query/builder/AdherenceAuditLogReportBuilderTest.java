package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceAuditLogReportBuilderTest extends BaseUnitTest{
    private AdherenceAuditLogReportBuilder adherenceAuditLogReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ExcelExporter excelExporter;

    @Mock
    private OutputStream outputStream;
    private List<AdherenceAuditLogSummary> adherenceAuditLogSummaries;
    private Map params;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceAuditLogReportBuilder = new AdherenceAuditLogReportBuilder(reportQueryService, excelExporter);

        adherenceAuditLogSummaries = asList(new AdherenceAuditLogSummary());

        params = new HashMap();
        params.put(AdherenceAuditLogReportBuilder.TEMPLATE_RESULT_KEY, adherenceAuditLogSummaries);
    }

    @Test
    public void shouldBuildAdherenceAuditLogReport() throws IOException {
        when(reportQueryService.getAdherenceAuditLogSummaries()).thenReturn(adherenceAuditLogSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(AdherenceAuditLogReportBuilder.ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        adherenceAuditLogReportBuilder.buildAdherenceAuditLogReport(outputStream);

        verify(reportQueryService).getAdherenceAuditLogSummaries();
        verify(workbook).write(outputStream);
        verify(outputStream).flush();
    }
}
