package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.export.query.builder.ContainerReportBuilder;
import org.motechproject.whp.reports.export.query.builder.ExcelReportBuilder;
import org.motechproject.whp.reports.export.query.model.ContainerSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bad.robot.excel.valuetypes.Coordinate.coordinate;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTime.now;
import static org.motechproject.whp.reports.date.WHPDateTime.date;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlDate;

public class ContainerTrackingReportExcelExportTest extends ExcelTest {

    DateTime now = now();

    @Test
    public void shouldCreateContainerTrackingReport() throws IOException {
        List<ContainerSummary> containerSummaries = asList(createContainerSummary());

        Map params = new HashMap();
        params.put("containerRecords", containerSummaries);
        String generatedDateTimeValue = date(now).value();
        params.put(ExcelReportBuilder.GENERATED_ON, generatedDateTimeValue);

        excelExporter.export(ContainerReportBuilder.CONTAINER_REPORT_TEMPLATE_FILE_NAME, params);

        Workbook workbook = excelExporter.export(ContainerReportBuilder.CONTAINER_REPORT_TEMPLATE_FILE_NAME, params);
        assertThat(getCellForCoordinate(coordinate(A, 1), workbook).getStringCellValue(), is(equalTo("Container Tracking Report")));
        assertThat(getCellForCoordinate(coordinate(A, 3), workbook).getStringCellValue(), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(getCellForCoordinate(coordinate(A, 5), workbook).getStringCellValue(), is(equalTo("containerId")));
        assertThat(getCellForCoordinate(coordinate(B, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(C, 5), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(coordinate(D, 5), workbook).getStringCellValue(), is(equalTo("district")));
        assertThat(getCellForCoordinate(coordinate(E, 5), workbook).getStringCellValue(), is(equalTo("submitterId")));
        assertThat(getCellForCoordinate(coordinate(F, 5), workbook).getStringCellValue(), is("Instance"));
        assertThat(getCellForCoordinate(coordinate(G, 5), workbook).getStringCellValue(), is("channelId"));
        assertThat(getCellForCoordinate(coordinate(H, 5), workbook).getStringCellValue(), is("patientId"));
        assertThat(getCellForCoordinate(coordinate(I, 5), workbook).getStringCellValue(), is("tbId"));
        assertThat(getCellForCoordinate(coordinate(J, 5), workbook).getStringCellValue(), is("mappingInstance"));
        assertThat(getCellForCoordinate(coordinate(K, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(L, 5), workbook).getStringCellValue(), is("testResult1"));
        assertThat(getCellForCoordinate(coordinate(M, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(N, 5), workbook).getStringCellValue(), is("testResult2"));
        assertThat(getCellForCoordinate(coordinate(O, 5), workbook).getStringCellValue(), is("labName"));
        assertThat(getCellForCoordinate(coordinate(P, 5), workbook).getStringCellValue(), is("labNumber"));
        assertThat(getCellForCoordinate(coordinate(Q, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(R, 5), workbook).getStringCellValue(), is("status"));
        assertThat(getCellForCoordinate(coordinate(S, 5), workbook).getStringCellValue(), is("reasonForClosure"));
        assertThat(getCellForCoordinate(coordinate(T, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(U, 5), workbook).getDateCellValue(), is(now.toDate()));
        assertThat(getCellForCoordinate(coordinate(V, 5), workbook).getStringCellValue(), is("diagnosis"));
        assertThat(getCellForCoordinate(coordinate(W, 5), workbook).getStringCellValue(), is("alternateDiagnosisCode"));
        assertThat(getCellForCoordinate(coordinate(X, 5), workbook).getStringCellValue(), is("alternateDiagnosisName"));

    }

    private ContainerSummary createContainerSummary() {
        ContainerSummary containerSummary = new ContainerSummary();
        containerSummary.setAlternateDiagnosisCode("alternateDiagnosisCode");
        containerSummary.setAlternateDiagnosisName("alternateDiagnosisName");
        containerSummary.setChannelId("channelId");
        containerSummary.setClosureDate(toSqlDate(now));
        containerSummary.setConsultationDate(toSqlDate(now));
        containerSummary.setContainerId("containerId");
        containerSummary.setDateIssuedOn(toSqlDate(now));
        containerSummary.setDiagnosis("diagnosis");
        containerSummary.setLabName("labName");
        containerSummary.setLabNumber("labNumber");
        containerSummary.setLabResultsCapturedOn(toSqlDate(now));
        containerSummary.setMappingInstance("mappingInstance");
        containerSummary.setPatientId("patientId");
        containerSummary.setProviderDistrict("district");
        containerSummary.setProviderId("providerId");
        containerSummary.setSmearTestDate1(toSqlDate(now));
        containerSummary.setSmearTestDate2(toSqlDate(now));
        containerSummary.setSmearTestResult1("testResult1");
        containerSummary.setSmearTestResult2("testResult2");
        containerSummary.setReasonForClosure("reasonForClosure");
        containerSummary.setStatus("status");
        containerSummary.setSubmitterId("submitterId");
        containerSummary.setTbId("tbId");
        containerSummary.setRegistrationInstance("Instance");

        return containerSummary;
    }

}
