package org.motechproject.whp.reports.export.query.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceRecordsReportBuilderTest {

    private AdherenceRecordsReportBuilder adherenceRecordsReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ReportBuilder reportBuilder;
    @Mock
    private OutputStream outputStream;

    private List<AdherenceRecordSummary> adherenceRecordSummaries;
    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceRecordsReportBuilder = new AdherenceRecordsReportBuilder(reportQueryService, reportBuilder);
        adherenceRecordSummaries = asList(new AdherenceRecordSummary());

        expectedParams = new HashMap();
        expectedParams.put("adherenceRecords", adherenceRecordSummaries);
    }

    @Test
    public void shouldBuildAdherenceRecordsReport() throws IOException {
        when(reportQueryService.getAdherenceRecordSummaries()).thenReturn(adherenceRecordSummaries);

        adherenceRecordsReportBuilder.buildAdherenceRecordReport(outputStream);

        verify(reportBuilder).build(outputStream, expectedParams, AdherenceRecordsReportBuilder.ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME);
    }
}
