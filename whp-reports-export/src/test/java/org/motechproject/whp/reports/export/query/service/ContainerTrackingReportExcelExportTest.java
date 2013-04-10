package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.motechproject.excel.builder.service.ExcelExporter;
import org.motechproject.util.DateUtil;

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
    public static final String START_DATE = "from_date";
    public static final String END_DATE = "to_date";
    public static final String PROVIDER_DISTRICT = "district";
    public static final String GENERATED_ON = "generated_on";

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
        DateTime generatedOn = new LocalDate().toDateTime(DateUtil.now());
        params.put(GENERATED_ON, generatedOn);
        String filtered_start_date = "2013-04-09 19:54:03.126";
        params.put(START_DATE, filtered_start_date);
        String filtered_end_date = "2014-04-09 19:54:03.126";
        params.put(END_DATE, filtered_end_date);
        String filtered_district = "Provider District";
        params.put(PROVIDER_DISTRICT, filtered_district);

        ExcelExporter excelExporter = new ExcelExporter();


        workbook = excelExporter.export(CONTAINER_REPORT_TEMPLATE_FILE_NAME, params);
        assertThat(stringValue(A, 1), is(equalTo("Container Tracking Report")));
        assertThat(stringValue(A, 2), is(equalTo("Generated as on "+ generatedOn)));
        assertThat(stringValue(A, 3), is(equalTo("Start Date: "+ filtered_start_date )));
        assertThat(stringValue(A, 4), is(equalTo("End Date: "+ filtered_end_date )));
        assertThat(stringValue(A, 5), is(equalTo("Provider District: "+ filtered_district)));

        assertThat(stringValue(A, 8), is(equalTo("containerId")));
        assertEquals(dateValue(B, 8), date);
        assertThat(stringValue(C, 8), is(equalTo("providerId")));
        assertThat(stringValue(D, 8), is(equalTo("Patna")));
        assertThat(stringValue(E, 8), is(equalTo("admin")));
        assertThat(stringValue(F, 8), is("Instance"));
        assertThat(stringValue(G, 8), is("IVR"));
        assertThat(stringValue(H, 8), is("givenPatientName"));
        assertThat(stringValue(I, 8), is("givenPatientId"));
        assertThat(numericValue(J, 8), is(45.0));
        assertThat(stringValue(K, 8), is("gender"));
        assertThat(stringValue(L, 8), is("patientId"));
        assertThat(stringValue(M, 8), is("tbId"));
        assertThat(stringValue(N, 8), is(""));
        assertThat(dateValue(O, 8), is(date));
        assertThat(stringValue(P, 8), is("result1"));
        assertThat(dateValue(Q, 8), is(date));
        assertThat(stringValue(R, 8), is("result2"));
        assertThat(stringValue(S, 8), is("labName"));
        assertThat(stringValue(T, 8), is("labNumber"));
        assertThat(stringValue(U, 8), is(timeStamp));
        assertThat(stringValue(V, 8), is("status"));
        assertThat(stringValue(W, 8), is("reasonForClosure"));
        assertThat(stringValue(X, 8), is(timeStamp));
        assertThat(dateValue(Y, 8), is(date));
        assertThat(stringValue(Z, 8), is("positive"));
        assertThat(stringValue(AA, 8), is("ac"));
        assertThat(stringValue(AB, 8), is("alternateDiagnosisText"));
    }

    public Workbook getWorkbook() {
        return workbook;
    }

}