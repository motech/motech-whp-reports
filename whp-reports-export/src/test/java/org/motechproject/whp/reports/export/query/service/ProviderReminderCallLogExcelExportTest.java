package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.ProviderReminderCallLogReportBuilder;
import org.motechproject.whp.reports.export.query.builder.ExcelReportBuilder;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;

import java.io.IOException;
import java.sql.Timestamp;
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
import static org.motechproject.model.DayOfWeek.Wednesday;
import static org.motechproject.whp.reports.date.WHPDateTime.date;

public class ProviderReminderCallLogExcelExportTest extends ExcelTest {

    DateTime now = now();
    private final Timestamp attemptTime = WHPDateTime.toSqlTimestamp(new DateTime(2013, 12, 12, 0, 0).withDayOfWeek(Wednesday.getValue()));
    private final Timestamp startTime = WHPDateTime.toSqlTimestamp(new DateTime(2013, 12, 13, 0, 0));

    @Test
    public void shouldCreateProviderReminderCallLogReport() throws IOException {
        List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = asList(createProviderReminderCallLog());

        Map params = new HashMap();
        params.put("callLogs", providerReminderCallLogSummaries);
        String generatedDateTimeValue = date(now).value();
        params.put(ExcelReportBuilder.GENERATED_ON, generatedDateTimeValue);

        excelExporter.export(ProviderReminderCallLogReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME, params);

        Workbook workbook = excelExporter.export(ProviderReminderCallLogReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME, params);
        assertThat(getCellForCoordinate(coordinate(A, 1), workbook).getStringCellValue(), is(equalTo("Provider Reminder Call Logs")));
        assertThat(getCellForCoordinate(coordinate(A, 3), workbook).getStringCellValue(), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(getCellForCoordinate(coordinate(A, 5), workbook).getStringCellValue(), is(equalTo("callId")));
        assertThat(getCellForCoordinate(coordinate(B, 5), workbook).getStringCellValue(), is(equalTo("providerId")));
        assertThat(getCellForCoordinate(coordinate(C, 5), workbook).getStringCellValue(), is(equalTo("district")));
        assertThat(getCellForCoordinate(coordinate(D, 5), workbook).getStringCellValue(), is(equalTo("reminderType")));
        assertThat(getCellForCoordinate(coordinate(E, 5), workbook).getStringCellValue(), is(Wednesday.name()));

        assertThat(getCellForCoordinate(coordinate(F, 5), workbook).getNumericCellValue(), is(41619.0));
        assertThat(getCellForCoordinate(coordinate(G, 5), workbook).getNumericCellValue(), is(41621.0));
        assertThat(getCellForCoordinate(coordinate(H, 5), workbook).getStringCellValue(), is(equalTo("Yes")));
        assertThat(getCellForCoordinate(coordinate(I, 5), workbook).getNumericCellValue(), is(new Double(3)));
        assertThat(getCellForCoordinate(coordinate(J, 5), workbook).getStringCellValue(), is(equalTo("Yes")));
        assertThat(getCellForCoordinate(coordinate(K, 5), workbook).getStringCellValue(), is(equalTo("type")));
        assertThat(getCellForCoordinate(coordinate(L, 5), workbook).getNumericCellValue(), is(new Double(1)));
    }

    private ProviderReminderCallLogSummary createProviderReminderCallLog() {

        ProviderReminderCallLogSummary providerReminderCallLogSummary = new ProviderReminderCallLogSummary();
        providerReminderCallLogSummary.setAdherenceReported("Y");
        providerReminderCallLogSummary.setAttempt(1);

        providerReminderCallLogSummary.setAttemptDateTime(attemptTime);
        providerReminderCallLogSummary.setStartDateTime(startTime);
        providerReminderCallLogSummary.setEndDateTime(new Timestamp(startTime.getTime() + 3000L));
        providerReminderCallLogSummary.setCallAnswered("Yes");
        providerReminderCallLogSummary.setCallId("callId");
        providerReminderCallLogSummary.setDisconnectionType("type");
        providerReminderCallLogSummary.setProviderId("providerId");
        providerReminderCallLogSummary.setReminderType("reminderType");
        providerReminderCallLogSummary.setDistrict("district");


        return providerReminderCallLogSummary;
    }


}
