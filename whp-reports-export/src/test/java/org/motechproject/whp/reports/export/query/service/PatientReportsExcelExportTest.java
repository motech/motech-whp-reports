package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.export.query.builder.PatientReportBuilder;
import org.motechproject.whp.reports.export.query.model.PatientSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.motechproject.whp.reports.export.query.builder.ExcelReportBuilder.GENERATED_ON;
import static org.motechproject.whp.reports.export.query.builder.PatientReportBuilder.*;

public class PatientReportsExcelExportTest extends ExcelTest{

    private Workbook workbook;

    @Test
    public void shouldCreateWorkbookForSummaryReport() throws IOException {

        PatientSummary patientSummary = new PatientSummary();
        patientSummary.setFirstName("John");
        patientSummary.setLastName("Doe");
        patientSummary.setAge(40);
        patientSummary.setGender("M");
        patientSummary.setPatientId("patientId");
        patientSummary.setTbId("tbId");
        patientSummary.setProviderId("providerId");
        patientSummary.setVillage("village");
        patientSummary.setProviderDistrict("providerDistrict");
        patientSummary.setTreatmentCategory("treatmentCategory");
        patientSummary.setTbRegistrationDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setTreatmentStartDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setDiseaseClass("disease class");
        patientSummary.setPatientType("patientType");
        patientSummary.setIpPillsTaken(12);
        patientSummary.setIpTotalDoses(16);
        patientSummary.setEipPillsTaken(3);
        patientSummary.setEipTotalDoses(4);
        patientSummary.setCpPillsTaken(13);
        patientSummary.setCpTotalDoses(16);
        patientSummary.setCumulativeMissedDoses(1);
        patientSummary.setTreatmentOutcome("treatmentOutcome");
        patientSummary.setTreatmentClosingDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = excelExporter.export(PatientReportBuilder.PATIENT_SUMMARY_TEMPLATE_FILE_NAME, params);
        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("All Patient Summary Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 7), is(equalTo("Number of patients found: 1")));
        assertThat(stringValue(ExcelColumnIndex.A, 9), is(equalTo("John Doe")));
        assertThat(numericValue(ExcelColumnIndex.B, 9), is(equalTo((double) 40)));
        assertThat(stringValue(ExcelColumnIndex.C, 9), is(equalTo("M")));
        assertThat(stringValue(ExcelColumnIndex.D, 9), is(equalTo("patientId")));
        assertThat(stringValue(ExcelColumnIndex.E, 9), is(equalTo("tbId")));
        assertThat(stringValue(ExcelColumnIndex.F, 9), is(equalTo("providerId")));
        assertThat(stringValue(ExcelColumnIndex.G, 9), is(equalTo("village")));
        assertThat(stringValue(ExcelColumnIndex.H, 9), is(equalTo("providerDistrict")));
        assertThat(stringValue(ExcelColumnIndex.I, 9), is(equalTo("treatmentCategory")));
        assertThat(stringValue(ExcelColumnIndex.J, 9), is(equalTo("preTreatmentResult")));
        assertThat(numericValue(ExcelColumnIndex.K, 9), is(equalTo((double) 1)));
        assertThat(stringValue(ExcelColumnIndex.N, 9), is(equalTo("disease class")));
        assertThat(stringValue(ExcelColumnIndex.O, 9), is(equalTo("patientType")));
        assertThat(stringValue(ExcelColumnIndex.P, 9), is(equalTo("15/20 (75.00%)")));
        assertThat(stringValue(ExcelColumnIndex.Q, 9), is(equalTo("13/16 (81.25%)")));
        assertThat(numericValue(ExcelColumnIndex.R, 9), is(equalTo((double) 1)));
        assertThat(stringValue(ExcelColumnIndex.S, 9), is(equalTo("treatmentOutcome")));
    }

    @Test
    public void shouldCreateWorkbookForRegistrationsReport() throws IOException {
        PatientSummary patientSummary = new PatientSummary();
        patientSummary.setFirstName("John");
        patientSummary.setLastName("Doe");
        patientSummary.setAge(40);
        patientSummary.setGender("M");
        patientSummary.setPatientId("patientId");
        patientSummary.setTbId("tbId");
        patientSummary.setProviderId("providerId");
        patientSummary.setVillage("village");
        patientSummary.setProviderDistrict("providerDistrict");
        patientSummary.setTreatmentCategory("treatmentCategory");
        patientSummary.setTbRegistrationDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setTreatmentStartDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setDiseaseClass("disease class");
        patientSummary.setPatientType("patientType");
        patientSummary.setIpPillsTaken(12);
        patientSummary.setIpTotalDoses(16);
        patientSummary.setCpPillsTaken(13);
        patientSummary.setCpTotalDoses(16);
        patientSummary.setCumulativeMissedDoses(1);
        patientSummary.setTreatmentOutcome("treatmentOutcome");
        patientSummary.setTreatmentClosingDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = excelExporter.export(PatientReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME, params);

        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("Patient Registrations Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 6), is(equalTo("Number of patients found: 1")));
        assertThat(stringValue(ExcelColumnIndex.A, 8), is(equalTo("John Doe")));
        assertThat(numericValue(ExcelColumnIndex.B, 8), is(equalTo((double) 40)));
        assertThat(stringValue(ExcelColumnIndex.C, 8), is(equalTo("M")));
        assertThat(stringValue(ExcelColumnIndex.D, 8), is(equalTo("patientId")));
        assertThat(stringValue(ExcelColumnIndex.E, 8), is(equalTo("tbId")));
        assertThat(stringValue(ExcelColumnIndex.G, 8), is(equalTo("providerId")));
        assertThat(stringValue(ExcelColumnIndex.H, 8), is(equalTo("village")));
        assertThat(stringValue(ExcelColumnIndex.I, 8), is(equalTo("providerDistrict")));
        assertThat(stringValue(ExcelColumnIndex.J, 8), is(equalTo("treatmentCategory")));
        assertThat(stringValue(ExcelColumnIndex.K, 8), is(equalTo("preTreatmentResult")));
        assertThat(stringValue(ExcelColumnIndex.L, 8), is(equalTo("disease class")));
        assertThat(stringValue(ExcelColumnIndex.M, 8), is(equalTo("patientType")));
    }

    @Test
    public void shouldCreateWorkbookForTreatmentClosedReport() throws IOException {
        PatientSummary patientSummary = new PatientSummary();
        patientSummary.setFirstName("John");
        patientSummary.setLastName("Doe");
        patientSummary.setAge(40);
        patientSummary.setGender("M");
        patientSummary.setPatientId("patientId");
        patientSummary.setTbId("tbId");
        patientSummary.setProviderId("providerId");
        patientSummary.setVillage("village");
        patientSummary.setProviderDistrict("providerDistrict");
        patientSummary.setTreatmentCategory("treatmentCategory");
        patientSummary.setTbRegistrationDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setTreatmentStartDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setDiseaseClass("disease class");
        patientSummary.setPatientType("patientType");
        patientSummary.setIpPillsTaken(12);
        patientSummary.setIpTotalDoses(16);
        patientSummary.setCpPillsTaken(13);
        patientSummary.setCpTotalDoses(16);
        patientSummary.setCumulativeMissedDoses(1);
        patientSummary.setTreatmentOutcome("treatmentOutcome");
        patientSummary.setTreatmentClosingDate(WHPDate.date("12/12/12").date().toDate());
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = excelExporter.export(PatientReportBuilder.PATIENT_CLOSED_TREATMENT_TEMPLATE_FILE_NAME, params);

        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("All Patient Closed Treatments Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 6), is(equalTo("Number of patients found: 1")));
        assertThat(stringValue(ExcelColumnIndex.A, 8), is(equalTo("John Doe")));
        assertThat(numericValue(ExcelColumnIndex.B, 8), is(equalTo((double) 40)));
        assertThat(stringValue(ExcelColumnIndex.C, 8), is(equalTo("M")));
        assertThat(stringValue(ExcelColumnIndex.D, 8), is(equalTo("patientId")));
        assertThat(stringValue(ExcelColumnIndex.E, 8), is(equalTo("tbId")));
        assertThat(stringValue(ExcelColumnIndex.G, 8), is(equalTo("providerId")));
        assertThat(stringValue(ExcelColumnIndex.H, 8), is(equalTo("village")));
        assertThat(stringValue(ExcelColumnIndex.I, 8), is(equalTo("providerDistrict")));
        assertThat(stringValue(ExcelColumnIndex.J, 8), is(equalTo("treatmentCategory")));
        assertThat(stringValue(ExcelColumnIndex.K, 8), is(equalTo("preTreatmentResult")));
        assertThat(stringValue(ExcelColumnIndex.L, 8), is(equalTo("disease class")));
        assertThat(stringValue(ExcelColumnIndex.M, 8), is(equalTo("patientType")));
        assertThat(stringValue(ExcelColumnIndex.O, 8), is(equalTo("treatmentOutcome")));
    }

    @Override
    Workbook getWorkbook() {
        return workbook;
    }
}
