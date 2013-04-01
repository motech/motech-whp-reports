package org.motechproject.whp.reports.export.query.builder;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.testing.utils.BaseUnitTest;
import org.motechproject.whp.reports.export.query.model.PatientReportRequest;
import org.motechproject.whp.reports.export.query.model.PatientSummary;
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

public class PatientReportBuilderTest extends BaseUnitTest {

    private PatientReportBuilder patientReportBuilder;

    @Mock
    private ReportQueryService reportQueryService;
    @Mock
    private WhpExcelReportBuilder whpExcelReportBuilder;

    @Mock
    private OutputStream outputStream;
    private String fromDate;
    private String toDate;
    private String providerDistrict;
    private List<PatientSummary> patientSummaries;
    private PatientReportRequest patientReportRequest;
    private Map expectedParams;

    @Before
    public void setUp() {
        initMocks(this);
        mockCurrentDate(new LocalDate(2012, 12, 25));
        fromDate = "20/10/2012";
        toDate = "30/11/2012";
        providerDistrict = "d1";

        patientReportBuilder = new PatientReportBuilder(reportQueryService, whpExcelReportBuilder);

        patientSummaries = asList(new PatientSummary());
        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict(providerDistrict);
        patientReportRequest.setFrom(fromDate);
        patientReportRequest.setTo(toDate);

        expectedParams = new HashMap();
        expectedParams.put(PatientReportBuilder.TEMPLATE_RESULT_KEY, patientSummaries);
        expectedParams.put(PatientReportBuilder.FROM_DATE, fromDate);
        expectedParams.put(PatientReportBuilder.TO_DATE, toDate);
        expectedParams.put(PatientReportBuilder.PROVIDER_DISTRICT, providerDistrict);
        expectedParams.put(PatientReportBuilder.TOTAL_ROWS, 1);
    }

    @Test
    public void shouldBuildPatientSummaryReport() throws IOException {
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);

        patientReportBuilder.buildSummaryReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(whpExcelReportBuilder).build(outputStream, expectedParams, PatientReportBuilder.PATIENT_SUMMARY_TEMPLATE_FILE_NAME);
    }

    @Test
    public void shouldBuildPatientRegistrationsReport() throws IOException {
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);

        patientReportBuilder.buildRegistrationsReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(whpExcelReportBuilder).build(outputStream, expectedParams, PatientReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME);
    }

    @Test
    public void shouldBuildPatientClosedTreatmentReport() throws IOException {
        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);

        patientReportBuilder.buildClosedTreatmentsReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(whpExcelReportBuilder).build(outputStream, expectedParams, PatientReportBuilder.PATIENT_CLOSED_TREATMENT_TEMPLATE_FILE_NAME);
    }

    @Test
    public void shouldBuildPatientRegistrationsReportWithoutUserSuppliedDateRange() throws IOException {
        expectedParams = new HashMap();
        expectedParams.put(PatientReportBuilder.TEMPLATE_RESULT_KEY, patientSummaries);
        expectedParams.put(PatientReportBuilder.PROVIDER_DISTRICT, providerDistrict);
        expectedParams.put(PatientReportBuilder.TOTAL_ROWS, 1);

        patientReportRequest = new PatientReportRequest();
        patientReportRequest.setDistrict(providerDistrict);

        when(reportQueryService.getPatientSummaries(patientReportRequest)).thenReturn(patientSummaries);

        patientReportBuilder.buildRegistrationsReport(patientReportRequest, outputStream);

        verify(reportQueryService).getPatientSummaries(patientReportRequest);
        verify(whpExcelReportBuilder).build(outputStream, expectedParams, PatientReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME);
    }
}
