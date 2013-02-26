package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;
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

public class AdherenceRecordsReportBuilderTest {

    private AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ExcelExporter excelExporter;

    @Mock
    private OutputStream outputStream;
    private List<AdherenceRecordSummary> adherenceRecordSummaries;
    private Map params;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceRecordsReportBuilder = new AdherenceRecordsReportBuilder(reportQueryService, excelExporter);

        adherenceRecordSummaries = asList(new AdherenceRecordSummary());

        params = new HashMap();
        params.put(AdherenceRecordsReportBuilder.TEMPLATE_RESULT_KEY, adherenceRecordSummaries);
    }

    @Test
    public void shouldBuildAdherenceRecordsReport() throws IOException {
        when(reportQueryService.getAdherenceRecordSummaries()).thenReturn(adherenceRecordSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(AdherenceRecordsReportBuilder.ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        adherenceRecordsReportBuilder.buildAdherenceRecordReport(outputStream);

        verify(reportQueryService).getAdherenceRecordSummaries();
        verify(workbook).write(outputStream);
        verify(outputStream).flush();
    }
}
