package org.motechproject.whp.reports.export.query.service;

import bad.robot.excel.valuetypes.Coordinate;
import bad.robot.excel.valuetypes.ExcelColumnIndex;
import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.AdherenceAuditLogReportBuilder;
import org.motechproject.whp.reports.export.query.builder.AdherenceRecordsReportBuilder;
import org.motechproject.whp.reports.export.query.model.AdherenceAuditLogSummary;
import org.motechproject.whp.reports.export.query.model.AdherenceRecordSummary;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AdherenceReportsExcelExportTest extends ExcelTest{

    @Test
    public void shouldCreateWorkbookForAdherenceAuditLogReport() throws IOException {
        AdherenceAuditLogSummary adherenceAuditLogSummary = new AdherenceAuditLogSummary();
        adherenceAuditLogSummary.setPatientId("patientId");
        adherenceAuditLogSummary.setProviderId("providerId");
        adherenceAuditLogSummary.setTbId("tbId");
        adherenceAuditLogSummary.setCreationTime(WHPDateTime.toSqlTimestamp(new DateTime("2013-02-22")));
        adherenceAuditLogSummary.setDoseDate(WHPDateTime.toSqlDate(new DateTime("2013-02-22")));
        adherenceAuditLogSummary.setUserId("userId");
        adherenceAuditLogSummary.setNumberOfDosesTaken(2);
        adherenceAuditLogSummary.setPillStatus("Taken");
        adherenceAuditLogSummary.setSourceOfChange("WEB");
        adherenceAuditLogSummary.setDistrict("providerDistrict");
        adherenceAuditLogSummary.setIsGivenByProvider("Y");

        List adherenceAuditLogSummaries = asList(adherenceAuditLogSummary);

        Map params = new HashMap();
        params.put("auditLogs", adherenceAuditLogSummaries);

        Workbook workbook = excelExporter.export(AdherenceAuditLogReportBuilder.ADHERENCE_AUDIT_LOG_SUMMARY_TEMPLATE_FILE_NAME, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("Adherence")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 3), workbook).getStringCellValue(), is(equalTo("tbId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 3), workbook).getStringCellValue(), is(equalTo("providerId")));
        //unformatted date time value
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.D, 3), workbook).getNumericCellValue(), is(equalTo(41327.0)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.E, 3), workbook).getNumericCellValue(), is(equalTo(41327.0)));

        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.F, 3), workbook).getStringCellValue(), is(equalTo("userId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.G, 3), workbook).getNumericCellValue(), is(equalTo((double) 2)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.H, 3), workbook).getStringCellValue(), is(equalTo("Taken")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.I, 3), workbook).getStringCellValue(), is(equalTo("WEB")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.J, 3), workbook).getStringCellValue(), is(equalTo("providerDistrict")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.K, 3), workbook).getStringCellValue(), is(equalTo("Yes")));
    }

    @Test
    public void shouldCreateWorkbookForAdherenceRecordReport() throws IOException {
        AdherenceRecordSummary adherenceRecordSummary = new AdherenceRecordSummary();
        adherenceRecordSummary.setDistrict("district");
        adherenceRecordSummary.setPatientId("patientId");
        adherenceRecordSummary.setAdherenceDate(WHPDateTime.toSqlDate(new DateTime("2013-02-22")));
        adherenceRecordSummary.setAdherenceValue("Taken");
        adherenceRecordSummary.setTbId("tbId");

        List adherenceRecordSummaries = asList(adherenceRecordSummary);

        Map params = new HashMap();
        params.put("adherenceRecords", adherenceRecordSummaries);

        Workbook workbook = excelExporter.export(AdherenceRecordsReportBuilder.ADHERENCE_RECORD_SUMMARY_TEMPLATE_FILE_NAME, params);
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 1), workbook).getStringCellValue(), is(equalTo("Adherence Records")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.A, 3), workbook).getStringCellValue(), is(equalTo("patientId")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.B, 3), workbook).getStringCellValue(), is(equalTo("tbId")));
        //unformatted date time value
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.C, 3), workbook).getNumericCellValue(), is(equalTo(41327.0)));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.D, 3), workbook).getStringCellValue(), is(equalTo("Taken")));
        assertThat(getCellForCoordinate(Coordinate.coordinate(ExcelColumnIndex.E, 3), workbook).getStringCellValue(), is(equalTo("district")));
    }

}
