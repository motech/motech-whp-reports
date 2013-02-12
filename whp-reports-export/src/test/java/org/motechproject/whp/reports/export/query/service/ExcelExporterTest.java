package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.Coordinate;
import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.export.query.model.PatientSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ExcelExporterTest {

    private final ExcelExporter excelExporter = new ExcelExporter();
    private final String templateFileName = "/xls/templates/patientSummaryReport.xls";

    @Test
    public void shouldCreateWorkbook() throws IOException {
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
        params.put("patients", patientSummaries);

        Workbook workbook = excelExporter.export(templateFileName, params);
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
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.K, 9), workbook).getNumericCellValue(), is(equalTo((double)1)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.N, 9), workbook).getStringCellValue(), is(equalTo("disease class")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.O, 9), workbook).getStringCellValue(), is(equalTo("patientType")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.P, 9), workbook).getStringCellValue(), is(equalTo("12/16 (75.00%)")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.Q, 9), workbook).getStringCellValue(), is(equalTo("13/16 (81.25%)")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.R, 9), workbook).getNumericCellValue(), is(equalTo((double)1)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.S, 9), workbook).getStringCellValue(), is(equalTo("treatmentOutcome")));
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
