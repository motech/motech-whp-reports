package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.Coordinate;
import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;
import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.model.PatientSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.motechproject.whp.reports.export.query.builder.PatientReportBuilder.*;

public class ExcelExporterTest {

    private final ExcelExporter excelExporter = new ExcelExporter();
    private final String summaryReportTemplateFileName = "/xls/templates/patientSummaryReport.xls";
    private final String registrationsReportTemplateFileName = "/xls/templates/patientRegistrationsReport.xls";
    private final String closeTreatmentsReportTemplateFileName = "/xls/templates/patientClosedTreatmentsReport.xls";
    private final String adherenceAuditLogReportTemplateFileName = "/xls/templates/adherenceAuditLogReport.xls";

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

        Workbook workbook = excelExporter.export(summaryReportTemplateFileName, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("All Patient Summary Data")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 2), workbook).getStringCellValue(), is(equalTo("Generated as on 25/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("Start Date: 01/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 4), workbook).getStringCellValue(), is(equalTo("End Date: 30/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 5), workbook).getStringCellValue(), is(equalTo("Provider District: d1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 7), workbook).getStringCellValue(), is(equalTo("Number of patients found: 1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 9), workbook).getStringCellValue(), is(equalTo("John Doe")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 9), workbook).getNumericCellValue(), is(equalTo((double) 40)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 9), workbook).getStringCellValue(), is(equalTo("M")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.D, 9), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.E, 9), workbook).getStringCellValue(), is(equalTo("tbId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.F, 9), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.G, 9), workbook).getStringCellValue(), is(equalTo("village")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.H, 9), workbook).getStringCellValue(), is(equalTo("providerDistrict")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.I, 9), workbook).getStringCellValue(), is(equalTo("treatmentCategory")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.J, 9), workbook).getStringCellValue(), is(equalTo("preTreatmentResult")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.K, 9), workbook).getNumericCellValue(), is(equalTo((double) 1)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.N, 9), workbook).getStringCellValue(), is(equalTo("disease class")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.O, 9), workbook).getStringCellValue(), is(equalTo("patientType")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.P, 9), workbook).getStringCellValue(), is(equalTo("15/20 (75.00%)")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.Q, 9), workbook).getStringCellValue(), is(equalTo("13/16 (81.25%)")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.R, 9), workbook).getNumericCellValue(), is(equalTo((double) 1)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.S, 9), workbook).getStringCellValue(), is(equalTo("treatmentOutcome")));
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

        Workbook workbook = excelExporter.export(registrationsReportTemplateFileName, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("Patient Registrations Data")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 2), workbook).getStringCellValue(), is(equalTo("Generated as on 25/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("Start Date: 01/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 4), workbook).getStringCellValue(), is(equalTo("End Date: 30/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 5), workbook).getStringCellValue(), is(equalTo("Provider District: d1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 6), workbook).getStringCellValue(), is(equalTo("Number of patients found: 1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 8), workbook).getStringCellValue(), is(equalTo("John Doe")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 8), workbook).getNumericCellValue(), is(equalTo((double) 40)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 8), workbook).getStringCellValue(), is(equalTo("M")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.D, 8), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.E, 8), workbook).getStringCellValue(), is(equalTo("tbId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.G, 8), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.H, 8), workbook).getStringCellValue(), is(equalTo("village")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.I, 8), workbook).getStringCellValue(), is(equalTo("providerDistrict")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.J, 8), workbook).getStringCellValue(), is(equalTo("treatmentCategory")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.K, 8), workbook).getStringCellValue(), is(equalTo("preTreatmentResult")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.L, 8), workbook).getStringCellValue(), is(equalTo("disease class")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.M, 8), workbook).getStringCellValue(), is(equalTo("patientType")));
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

        Workbook workbook = excelExporter.export(closeTreatmentsReportTemplateFileName, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("All Patient Closed Treatments Data")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 2), workbook).getStringCellValue(), is(equalTo("Generated as on 25/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("Start Date: 01/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 4), workbook).getStringCellValue(), is(equalTo("End Date: 30/12/2012")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 5), workbook).getStringCellValue(), is(equalTo("Provider District: d1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 6), workbook).getStringCellValue(), is(equalTo("Number of patients found: 1")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 8), workbook).getStringCellValue(), is(equalTo("John Doe")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 8), workbook).getNumericCellValue(), is(equalTo((double) 40)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 8), workbook).getStringCellValue(), is(equalTo("M")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.D, 8), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.E, 8), workbook).getStringCellValue(), is(equalTo("tbId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.G, 8), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.H, 8), workbook).getStringCellValue(), is(equalTo("village")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.I, 8), workbook).getStringCellValue(), is(equalTo("providerDistrict")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.J, 8), workbook).getStringCellValue(), is(equalTo("treatmentCategory")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.K, 8), workbook).getStringCellValue(), is(equalTo("preTreatmentResult")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.L, 8), workbook).getStringCellValue(), is(equalTo("disease class")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.M, 8), workbook).getStringCellValue(), is(equalTo("patientType")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.O, 8), workbook).getStringCellValue(), is(equalTo("treatmentOutcome")));
    }

    @Test
    public void shouldCreateWorkbookForAdherenceAuditLogReport() throws IOException {
        AdherenceAuditLogSummary adherenceAuditLogSummary = new AdherenceAuditLogSummary();
        adherenceAuditLogSummary.setPatientId("patientId");
        adherenceAuditLogSummary.setProviderId("providerId");
        adherenceAuditLogSummary.setTbId("tbId");
        adherenceAuditLogSummary.setCreationTime(WHPDateTime.toSqlTimestamp(new DateTime()));
        adherenceAuditLogSummary.setDoseDate(WHPDateTime.toSqlDate(new DateTime()));
        adherenceAuditLogSummary.setUserId("userId");
        adherenceAuditLogSummary.setNumberOfDosesTaken(2);
        adherenceAuditLogSummary.setPillStatus("Taken");
        adherenceAuditLogSummary.setSourceOfChange("WEB");
        adherenceAuditLogSummary.setDistrict("providerDistrict");

        List adherenceAuditLogSummaries = asList(adherenceAuditLogSummary);

        Map params = new HashMap();
        params.put(AdherenceAuditLogReportBuilder.TEMPLATE_RESULT_KEY, adherenceAuditLogSummaries);

        Workbook workbook = excelExporter.export(adherenceAuditLogReportTemplateFileName, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("Adherence")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 3), workbook).getStringCellValue(), is(equalTo("tbId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 3), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.F, 3), workbook).getStringCellValue(), is(equalTo("userId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.G, 3), workbook).getNumericCellValue(), is(equalTo((double) 2)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.H, 3), workbook).getStringCellValue(), is(equalTo("Taken")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.I, 3), workbook).getStringCellValue(), is(equalTo("WEB")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.J, 3), workbook).getStringCellValue(), is(equalTo("providerDistrict")));
    }

    public static org.apache.poi.ss.usermodel.Cell getCellForCoordinate(Coordinate coordinate, Workbook workbook) throws IOException {
        org.apache.poi.ss.usermodel.Row row = getRowForCoordinate(coordinate, workbook);
        return row.getCell(coordinate.getColumn().value());
    }

    public static org.apache.poi.ss.usermodel.Row getRowForCoordinate(Coordinate coordinate, Workbook workbook) throws IOException {
        Sheet sheet = workbook.getSheetAt(coordinate.getSheet().value());
        org.apache.poi.ss.usermodel.Row row = sheet.getRow(coordinate.getRow().value());
        if (row == null)
            throw new IllegalStateException("expected to find a row");
        return row;
    }
}
