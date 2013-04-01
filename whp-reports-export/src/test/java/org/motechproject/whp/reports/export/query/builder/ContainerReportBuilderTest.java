package org.motechproject.whp.reports.export.query.builder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.ContainerSummary;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerReportBuilderTest {
    ContainerReportBuilder containerReportBuilder;

    @Mock
    ReportQueryService reportQueryService;
    @Mock
    WhpExcelReportBuilder excelExportBuilder;
    @Mock
    OutputStream outputStream;

    private List<ContainerSummary> containerSummaries;
    private Map params;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        containerReportBuilder = new ContainerReportBuilder(reportQueryService, excelExportBuilder);

        params = new HashMap();
        containerSummaries = asList(new ContainerSummary());
        params.put("containerRecords",containerSummaries);
    }

    @Test
    public void shouldCreateContainerReport() {
        when(reportQueryService.getContainerSummaries()).thenReturn(containerSummaries);

        containerReportBuilder.build(outputStream);

        verify(excelExportBuilder).build(outputStream, params, ContainerReportBuilder.CONTAINER_REPORT_TEMPLATE_FILE_NAME);
    }
}
