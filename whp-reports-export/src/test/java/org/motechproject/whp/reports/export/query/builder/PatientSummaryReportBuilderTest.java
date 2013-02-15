package org.motechproject.whp.reports.export.query.builder;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.testing.utils.BaseUnitTest;
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
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PatientSummaryReportBuilderTest extends BaseUnitTest {

    private PatientSummaryReportBuilder patientSummaryReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private ExcelExporter excelExporter;

    @Mock
    private OutputStream outputStream;
    private String fromDate;
    private String toDate;
    private String providerDistrict;
    private List<PatientSummary> patientSummaries;
    private PatientReportRequest patientReportRequest;
    private Map params;

    @Before
    public void setUp() {
        initMocks(this);
        mockCurrentDate(new LocalDate(2012, 12, 25));
        fromDate = "20/10/2012";
        toDate = "30/11/2012";
        providerDistrict = "d1";

        patientSummaryReportBuilder = new PatientSummaryReportBuilder(reportQueryService, excelExporter);

        patientSummaries = asList(new PatientSummary());
        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict(providerDistrict);
        patientReportRequest.setFrom(fromDate);
        patientReportRequest.setTo(toDate);

        params = new HashMap();
        params.put(PatientSummaryReportBuilder.TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(PatientSummaryReportBuilder.GENERATED_ON, "25/12/2012");
        params.put(PatientSummaryReportBuilder.FROM_DATE, fromDate);
        params.put(PatientSummaryReportBuilder.TO_DATE, toDate);
        params.put(PatientSummaryReportBuilder.PROVIDER_DISTRICT, providerDistrict);
        params.put(PatientSummaryReportBuilder.TOTAL_ROWS, 1);
    }

    @Test
    public void shouldBuildPatientSummaryReport() throws IOException {
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(PatientSummaryReportBuilder.PATIENT_SUMMARY_TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        patientSummaryReportBuilder.buildSummaryReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(workbook).write(outputStream);
        verify(outputStream).flush();
    }

    @Test
    public void shouldBuildPatientRegistrationsReport() throws IOException {
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(PatientSummaryReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        patientSummaryReportBuilder.buildRegistrationsReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(workbook).write(outputStream);
        verify(outputStream).flush();
    }

    @Test
    public void shouldBuildPatientRegistrationsReportWithoutUserSuppliedDateRange() throws IOException {
        params = new HashMap();
        params.put(PatientSummaryReportBuilder.TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(PatientSummaryReportBuilder.GENERATED_ON, "25/12/2012");
        params.put(PatientSummaryReportBuilder.FROM_DATE, "28/06/2012");
        params.put(PatientSummaryReportBuilder.TO_DATE, "25/12/2012");
        params.put(PatientSummaryReportBuilder.PROVIDER_DISTRICT, providerDistrict);
        params.put(PatientSummaryReportBuilder.TOTAL_ROWS, 1);

        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict(providerDistrict);

        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);
        Workbook workbook = mock(Workbook.class);
        when(excelExporter.export(PatientSummaryReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME, params)).thenReturn(workbook);

        patientSummaryReportBuilder.buildRegistrationsReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(workbook).write(outputStream);
        verify(outputStream).flush();
    }
}
