package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.PatientReportBuilder;
import org.motechproject.whp.reports.export.query.model.PatientSummary;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTime.now;
import static org.motechproject.whp.reports.export.query.builder.WhpExcelReportBuilder.GENERATED_ON;
import static org.motechproject.whp.reports.export.query.builder.PatientReportBuilder.*;

public class PatientReportsExcelExportTest extends ExcelTest{

    private Workbook workbook;
    private DateTime now = now();
    private Date testDate = now.toDate();
    private Timestamp testDateTime = WHPDateTime.toSqlTimestamp(now);

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
        patientSummary.setTbRegistrationDate(testDate);
        patientSummary.setCreationDate(testDateTime);
        patientSummary.setTreatmentStartDate(testDate);
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
        patientSummary.setTreatmentClosingDate(testDate);
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);
        patientSummary.setCloseTreatmentRemarks("you are free my child");
        patientSummary.setDistrictWithCode("district_with_code");
        patientSummary.setTbUnitWithCode("tb_with_code");
        patientSummary.setEpSite("ep_site");
        patientSummary.setOtherInvestigations("others");
        patientSummary.setPreviousTreatmentHistory("treatment_history");
        patientSummary.setHivStatus("hiv_status");
        patientSummary.setMembersBelowSixYears(6);
        patientSummary.setProviderType("provider_type");
        patientSummary.setCmfDoctor("cmf doctor");
        patientSummary.setXpertTestResult("xpert test result");
        patientSummary.setXpertDeviceNumber("xpert device number");
        patientSummary.setXpertTestDate(testDate);
        patientSummary.setRifResistanceResult("rif resistance result");

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = whpExcelExporter.export(PatientReportBuilder.PATIENT_SUMMARY_TEMPLATE_FILE_NAME, params);
        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("All Patient Summary Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 7), is(equalTo("Number of records: 1")));
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
        assertThat(dateValue(ExcelColumnIndex.L, 9), is(testDate));
        assertThat(dateValue(ExcelColumnIndex.M, 9), is(new Date(testDateTime.getTime())));
        assertThat(dateValue(ExcelColumnIndex.N, 9), is(testDate));
        assertThat(stringValue(ExcelColumnIndex.O, 9), is(equalTo("disease class")));
        assertThat(stringValue(ExcelColumnIndex.P, 9), is(equalTo("patientType")));
        assertThat(stringValue(ExcelColumnIndex.Q, 9), is(equalTo("15/20 (75.00%)")));
        assertThat(stringValue(ExcelColumnIndex.R, 9), is(equalTo("13/16 (81.25%)")));
        assertThat(numericValue(ExcelColumnIndex.S, 9), is(equalTo((double) 1)));
        assertThat(stringValue(ExcelColumnIndex.T, 9), is(equalTo("district_with_code")));
        assertThat(stringValue(ExcelColumnIndex.U, 9), is(equalTo("tb_with_code")));
        assertThat(stringValue(ExcelColumnIndex.V, 9), is(equalTo("ep_site")));
        assertThat(stringValue(ExcelColumnIndex.W, 9), is(equalTo("others")));
        assertThat(stringValue(ExcelColumnIndex.X, 9), is(equalTo("treatment_history")));
        assertThat(stringValue(ExcelColumnIndex.Y, 9), is(equalTo("hiv_status")));
        assertThat(numericValue(ExcelColumnIndex.Z, 9), is(equalTo((double)6)));
        assertThat(stringValue(ExcelColumnIndex.AA, 9), is(equalTo("cmf doctor")));
        assertThat(stringValue(ExcelColumnIndex.AB, 9), is(equalTo("provider_type")));
        assertThat(stringValue(ExcelColumnIndex.AC, 9), is(equalTo("xpert device number")));
        assertThat(dateValue(ExcelColumnIndex.AD, 9), is(testDate));
        assertThat(stringValue(ExcelColumnIndex.AE, 9), is(equalTo("xpert test result")));
        assertThat(stringValue(ExcelColumnIndex.AF, 9), is(equalTo("rif resistance result")));
        assertThat(stringValue(ExcelColumnIndex.AG, 9), is(equalTo("treatmentOutcome")));
        assertThat(dateValue(ExcelColumnIndex.AH, 9), is(testDate));
        assertThat(stringValue(ExcelColumnIndex.AI, 9), is(equalTo("you are free my child")));
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
        patientSummary.setTbRegistrationDate(testDate);
        patientSummary.setTreatmentStartDate(testDate);
        patientSummary.setDiseaseClass("disease class");
        patientSummary.setPatientType("patientType");
        patientSummary.setIpPillsTaken(12);
        patientSummary.setIpTotalDoses(16);
        patientSummary.setCpPillsTaken(13);
        patientSummary.setCpTotalDoses(16);
        patientSummary.setCumulativeMissedDoses(1);
        patientSummary.setTreatmentOutcome("treatmentOutcome");
        patientSummary.setTreatmentClosingDate(testDate);
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);
        patientSummary.setXpertTestResult("xpert test result");
        patientSummary.setXpertDeviceNumber("xpert device number");
        patientSummary.setXpertTestDate(testDate);
        patientSummary.setRifResistanceResult("rif resistance result");

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = whpExcelExporter.export(PatientReportBuilder.PATIENT_REGISTRATIONS_TEMPLATE_FILE_NAME, params);

        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("Patient Registrations Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 6), is(equalTo("Number of records: 1")));
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
        assertThat(stringValue(ExcelColumnIndex.N, 8), is(equalTo("xpert device number")));
        assertThat(dateValue(ExcelColumnIndex.O, 8), is(testDate));
        assertThat(stringValue(ExcelColumnIndex.P, 8), is(equalTo("xpert test result")));
        assertThat(stringValue(ExcelColumnIndex.Q, 8), is(equalTo("rif resistance result")));
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
        patientSummary.setTbRegistrationDate(testDate);
        patientSummary.setTreatmentStartDate(testDate);
        patientSummary.setDiseaseClass("disease class");
        patientSummary.setPatientType("patientType");
        patientSummary.setIpPillsTaken(12);
        patientSummary.setIpTotalDoses(16);
        patientSummary.setCpPillsTaken(13);
        patientSummary.setCpTotalDoses(16);
        patientSummary.setCumulativeMissedDoses(1);
        patientSummary.setTreatmentOutcome("treatmentOutcome");
        patientSummary.setTreatmentClosingDate(testDate);
        patientSummary.setPreTreatmentSputumResult("preTreatmentResult");
        patientSummary.setPreTreatmentWeight((double) 1);
        patientSummary.setXpertTestResult("xpert test result");
        patientSummary.setXpertDeviceNumber("xpert device number");
        patientSummary.setXpertTestDate(testDate);
        patientSummary.setRifResistanceResult("rif resistance result");

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put(TEMPLATE_RESULT_KEY, patientSummaries);
        params.put(GENERATED_ON, "25/12/2012");
        params.put(FROM_DATE, "01/12/2012");
        params.put(TO_DATE, "30/12/2012");
        params.put(PROVIDER_DISTRICT, "d1");
        params.put(TOTAL_ROWS, "1");

        workbook = whpExcelExporter.export(PatientReportBuilder.PATIENT_CLOSED_TREATMENT_TEMPLATE_FILE_NAME, params);

        assertThat(stringValue(ExcelColumnIndex.A, 1), is(equalTo("All Patient Closed Treatments Data")));
        assertThat(stringValue(ExcelColumnIndex.A, 2), is(equalTo("Generated as on 25/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 3), is(equalTo("Start Date: 01/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 4), is(equalTo("End Date: 30/12/2012")));
        assertThat(stringValue(ExcelColumnIndex.A, 5), is(equalTo("Provider District: d1")));
        assertThat(stringValue(ExcelColumnIndex.A, 6), is(equalTo("Number of records: 1")));
        assertThat(stringValue(ExcelColumnIndex.A, 8), is(equalTo("John Doe")));
        assertThat(numericValue(ExcelColumnIndex.B, 8), is(equalTo((double) 40)));
        assertThat(stringValue(ExcelColumnIndex.C, 8), is(equalTo("M")));
        assertThat(stringValue(ExcelColumnIndex.D, 8), is(equalTo("patientId")));
        assertThat(stringValue(ExcelColumnIndex.E, 8), is(equalTo("tbId")));
        assertThat(dateValue(ExcelColumnIndex.F, 8), is(equalTo(testDate)));
        assertThat(stringValue(ExcelColumnIndex.G, 8), is(equalTo("providerId")));
        assertThat(stringValue(ExcelColumnIndex.H, 8), is(equalTo("village")));
        assertThat(stringValue(ExcelColumnIndex.I, 8), is(equalTo("providerDistrict")));
        assertThat(stringValue(ExcelColumnIndex.J, 8), is(equalTo("treatmentCategory")));
        assertThat(stringValue(ExcelColumnIndex.K, 8), is(equalTo("preTreatmentResult")));
        assertThat(stringValue(ExcelColumnIndex.L, 8), is(equalTo("disease class")));
        assertThat(stringValue(ExcelColumnIndex.M, 8), is(equalTo("patientType")));
        assertThat(stringValue(ExcelColumnIndex.N, 8), is(equalTo("xpert device number")));
        assertThat(dateValue(ExcelColumnIndex.O, 8), is(testDate));
        assertThat(stringValue(ExcelColumnIndex.P, 8), is(equalTo("xpert test result")));
        assertThat(stringValue(ExcelColumnIndex.Q, 8), is(equalTo("rif resistance result")));
        assertThat(stringValue(ExcelColumnIndex.R, 8), is(equalTo("treatmentOutcome")));
        assertThat(dateValue(ExcelColumnIndex.S, 8), is(equalTo(testDate)));
    }

    @Override
    Workbook getWorkbook() {
        return workbook;
    }
}
