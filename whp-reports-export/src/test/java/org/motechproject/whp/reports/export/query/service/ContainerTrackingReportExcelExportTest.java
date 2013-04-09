package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.excel.builder.service.ExcelExporter;

import java.io.IOException;
import java.util.*;

import static bad.robot.excel.valuetypes.ExcelColumnIndex.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

public class ContainerTrackingReportExcelExportTest extends ExcelTest {

    Date date = new LocalDate().toDate();
    String timeStamp = "2013-04-09 19:54:03.126";


    private Workbook workbook;
    private String TEMPLATE_RESULT_KEY = "data";
    public static final String CONTAINER_REPORT_TEMPLATE_FILE_NAME = "/xls/templates/containerReport.xls";

    @Test
    public void shouldCreateContainerTrackingReport() throws IOException {

        Map<String, Object> containerResult = new HashMap(){{
                put("patient_id", "patientId");
                put("reason_for_closure", "reasonForClosure");
                put("alternate_diagnosis_code", "ac");
                put("alternate_diagnosis_name", "alternateDiagnosisText");
                put("channel_id","IVR");
                put("closure_date", timeStamp);
                put("consultation_date", date);
                put("container_id", "containerId");
                put("date_issued_on", date);
                put("diagnosis", "positive");
                put("lab_name", "labName");
                put("lab_number", "labNumber");
                put("lab_results_captured_on", timeStamp);
                put("mapping_instance", null);
                put("provider_district", "Patna");
                put("provider_id", "providerId");
                put("tb_id", "tbId");
                put("registration_instance", "Instance");
                put("smear_test_date_1", date);
                put("smear_test_date_2", date);
                put("smear_test_result_1", "result1");
                put("smear_test_result_2", "result2");
                put("status", "status");
                put("submitter_id", "admin" );
                put("given_patient_name","givenPatientName");
                put("given_patient_id", "givenPatientId");
                put("given_patient_age", 45);
                put("given_gender","gender");

        }};
        
        List<Map<String, Object>> resultSet = new ArrayList<>();
        resultSet.add(containerResult);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, resultSet);

        ExcelExporter excelExporter = new ExcelExporter();


        workbook = excelExporter.export(CONTAINER_REPORT_TEMPLATE_FILE_NAME, params);
        assertThat(stringValue(A, 1), is(equalTo("Container Tracking Report")));
//        assertThat(stringValue(A, 3), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(stringValue(A, 5), is(equalTo("containerId")));
        assertEquals(dateValue(B, 5), date);
        assertThat(stringValue(C, 5), is(equalTo("providerId")));
        assertThat(stringValue(D, 5), is(equalTo("Patna")));
        assertThat(stringValue(E, 5), is(equalTo("admin")));
        assertThat(stringValue(F, 5), is("Instance"));
        assertThat(stringValue(G, 5), is("IVR"));
        assertThat(stringValue(H, 5), is("givenPatientName"));
        assertThat(stringValue(I, 5), is("givenPatientId"));
        assertThat(numericValue(J, 5), is(45.0));
        assertThat(stringValue(K, 5), is("gender"));
        assertThat(stringValue(L, 5), is("patientId"));
        assertThat(stringValue(M, 5), is("tbId"));
        assertThat(stringValue(N, 5), is(""));
        assertThat(dateValue(O, 5), is(date));
        assertThat(stringValue(P, 5), is("result1"));
        assertThat(dateValue(Q, 5), is(date));
        assertThat(stringValue(R, 5), is("result2"));
        assertThat(stringValue(S, 5), is("labName"));
        assertThat(stringValue(T, 5), is("labNumber"));
        assertThat(stringValue(U, 5), is(timeStamp));
        assertThat(stringValue(V, 5), is("status"));
        assertThat(stringValue(W, 5), is("reasonForClosure"));
        assertThat(stringValue(X, 5), is(timeStamp));
        assertThat(dateValue(Y, 5), is(date));
        assertThat(stringValue(Z, 5), is("positive"));
        assertThat(stringValue(AA, 5), is("ac"));
        assertThat(stringValue(AB, 5), is("alternateDiagnosisText"));
    }

    public Workbook getWorkbook() {
        return workbook;
    }

}