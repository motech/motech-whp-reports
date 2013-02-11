package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.Coordinate;
import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
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
        patientSummary.setName("Mac");

        List patientSummaries = asList(patientSummary);

        Map params = new HashMap();
        params.put("patients", patientSummaries);

        Workbook workbook = excelExporter.export(templateFileName, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 9), workbook).getStringCellValue(), is(equalTo("Mac")));
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

    public static String getCellDataFormatAtCoordinate(Coordinate coordinate, Workbook workbook) throws IOException {
        return getCellForCoordinate(coordinate, workbook).getCellStyle().getDataFormatString();
    }
}
