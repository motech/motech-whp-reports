package org.motechproject.whp.reports.export.query.service;

import static bad.robot.excel.valuetypes.ExcelColumnIndex.A;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.B;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.C;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.D;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.E;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.F;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.G;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.H;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.I;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.J;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.K;
import static bad.robot.excel.valuetypes.ExcelColumnIndex.L;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTime.now;
import static org.motechproject.model.DayOfWeek.Wednesday;
import static org.motechproject.whp.reports.date.WHPDateTime.date;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.ProviderReportBuilder;
import org.motechproject.whp.reports.export.query.builder.WhpExcelReportBuilder;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;

public class ProviderReminderCallLogExcelExportTest extends ExcelTest {

    private Workbook workbook;

    DateTime now = now();
    private final Timestamp attemptTime = WHPDateTime.toSqlTimestamp(new DateTime(2013, 12, 12, 0, 0).withDayOfWeek(Wednesday.getValue()));
    private final Timestamp startTime = WHPDateTime.toSqlTimestamp(new DateTime(2013, 12, 13, 0, 0));

    @Test
    public void shouldCreateProviderReminderCallLogReport() throws IOException {
        List<ProviderReminderCallLogSummary> providerReminderCallLogSummaries = asList(createProviderReminderCallLog());

        Map params = new HashMap();
        params.put("callLogs", providerReminderCallLogSummaries);
        String generatedDateTimeValue = date(now).value();
        params.put(WhpExcelReportBuilder.GENERATED_ON, generatedDateTimeValue);

        workbook = whpExcelExporter.export(ProviderReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME, params);

        assertThat(stringValue(A, 1), is(equalTo("Provider Reminder Call Logs")));
        assertThat(stringValue(A, 3), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(stringValue(A, 4), is(equalTo("Provider District: ")));
        assertThat(stringValue(A, 5), is(equalTo("Start Date: ")));
        assertThat(stringValue(A, 6), is(equalTo("End Date: ")));
        assertThat(stringValue(A, 7), is(equalTo("Number of records: ")));
        assertThat(stringValue(A, 9), is(equalTo("Call Id")));
        assertThat(stringValue(B, 9), is(equalTo("Provider Id")));
        assertThat(stringValue(C, 9), is(equalTo("Provider district")));
        assertThat(stringValue(D, 9), is(equalTo("Reminder Type")));
        assertThat(stringValue(E, 9), is("Reminder Day"));
        assertThat(stringValue(F, 9), is("Call Attempt Time"));
        assertThat(stringValue(G, 9), is("Call Start Time"));
        assertThat(stringValue(H, 9), is("Call Answered"));
        assertThat(stringValue(I, 9), is("Duration Of Call (seconds)"));
        assertThat(stringValue(J, 9), is("Adherence Reported After Call"));
        assertThat(stringValue(K, 9), is("Disconnection Type"));
        assertThat(stringValue(L, 9), is("Call Attempt Number"));
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

    @Override
    Workbook getWorkbook() {
        return workbook;
    }
}
