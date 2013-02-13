package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
import org.motechproject.whp.reports.export.query.service.ExcelExporter;
import org.motechproject.whp.reports.export.query.service.ReportQueryService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientSummaryReportBuilderTest{

    private PatientSummaryReportBuilder patientSummaryReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ExcelExporter excelExporter;

    @Mock
    private OutputStream outputStream;

    @Before
    public void setUp() {
        initMocks(this);
        patientSummaryReportBuilder = new PatientSummaryReportBuilder(reportQueryService, excelExporter);
    }

    @Test
    public void shouldBuildPatientSummaryReport() throws IOException {
        List<PatientSummary> patientSummaries = asList(new PatientSummary());
        PatientReportRequest patientReportRequest = mock(PatientReportRequest.class);
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);
        Map<String, List<PatientSummary>> params = new HashMap<>();
        params.put("patients", patientSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(PatientSummaryReportBuilder.TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        patientSummaryReportBuilder.build(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        ArgumentCaptor<Map> mapArgumentCaptor = ArgumentCaptor.forClass(Map.class);
        ArgumentCaptor<String> templateArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(excelExporter).export(templateArgumentCaptor.capture(), mapArgumentCaptor.capture());
        Map map = mapArgumentCaptor.getValue();
        String templateFileName = templateArgumentCaptor.getValue();

        verify(workbook).write(outputStream);
        verify(outputStream).flush();

        assertEquals(map.get("patients"), patientSummaries);
        assertEquals("/xls/templates/patientSummaryReport.xls", templateFileName);
    }
}
