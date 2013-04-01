package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.export.query.builder.ContainerReportBuilder;
import org.motechproject.whp.reports.export.query.builder.WhpExcelReportBuilder;
import org.motechproject.whp.reports.export.query.model.ContainerSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Workbook workbook;

    @Test
    public void shouldCreateContainerTrackingReport() throws IOException {
        List<ContainerSummary> containerSummaries = asList(createContainerSummary());

        Map params = new HashMap();
        params.put("containerRecords", containerSummaries);
        String generatedDateTimeValue = date(now).value();
        params.put(WhpExcelReportBuilder.GENERATED_ON, generatedDateTimeValue);

        workbook = whpExcelExporter.export(ContainerReportBuilder.CONTAINER_REPORT_TEMPLATE_FILE_NAME, params);
        assertThat(stringValue(A, 1), is(equalTo("Container Tracking Report")));
        assertThat(stringValue(A, 3), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(stringValue(A, 5), is(equalTo("containerId")));
        assertThat(dateValue(B, 5), is(now.toDate()));
        assertThat(stringValue(C, 5), is(equalTo("providerId")));
        assertThat(stringValue(D, 5), is(equalTo("district")));
        assertThat(stringValue(E, 5), is(equalTo("submitterId")));
        assertThat(stringValue(F, 5), is("Instance"));
        assertThat(stringValue(G, 5), is("channelId"));
        assertThat(stringValue(H, 5), is("patientId"));
        assertThat(stringValue(I, 5), is("tbId"));
        assertThat(stringValue(J, 5), is("mappingInstance"));
        assertThat(dateValue(K, 5), is(now.toDate()));
        assertThat(stringValue(L, 5), is("testResult1"));
        assertThat(dateValue(M, 5), is(now.toDate()));
        assertThat(stringValue(N, 5), is("testResult2"));
        assertThat(stringValue(O, 5), is("labName"));
        assertThat(stringValue(P, 5), is("labNumber"));
        assertThat(dateValue(Q, 5), is(now.toDate()));
        assertThat(stringValue(R, 5), is("status"));
        assertThat(stringValue(S, 5), is("reasonForClosure"));
        assertThat(dateValue(T, 5), is(now.toDate()));
        assertThat(dateValue(U, 5), is(now.toDate()));
        assertThat(stringValue(V, 5), is("diagnosis"));
        assertThat(stringValue(W, 5), is("alternateDiagnosisCode"));
        assertThat(stringValue(X, 5), is("alternateDiagnosisName"));
        assertThat(stringValue(Y, 5), is("name"));
        assertThat(stringValue(Z, 5), is("id"));
        assertThat(numericValue(AA, 5), is(98.0));
        assertThat(stringValue(AB, 5), is("gender"));
    }

    public Workbook getWorkbook() {
        return workbook;
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
        containerSummary.setGivenPatientName("name");
        containerSummary.setGivenPatientId("id");
        containerSummary.setGivenPatientAge(98);
        containerSummary.setGivenGender("gender");

        return containerSummary;
    }
}
