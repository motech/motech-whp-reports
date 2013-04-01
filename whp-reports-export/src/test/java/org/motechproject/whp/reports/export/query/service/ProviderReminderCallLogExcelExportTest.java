package org.motechproject.whp.reports.export.query.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.export.query.builder.WhpExcelReportBuilder;
import org.motechproject.whp.reports.export.query.builder.ProviderReminderCallLogReportBuilder;
import org.motechproject.whp.reports.export.query.model.ProviderReminderCallLogSummary;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bad.robot.excel.valuetypes.ExcelColumnIndex.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.joda.time.DateTime.now;
import static org.motechproject.model.DayOfWeek.Wednesday;
import static org.motechproject.whp.reports.date.WHPDateTime.date;

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

        workbook = whpExcelExporter.export(ProviderReminderCallLogReportBuilder.PROVIDER_REMINDER_CALL_LOG_TEMPLATE_NAME, params);

        assertThat(stringValue(A, 1), is(equalTo("Provider Reminder Call Logs")));
        assertThat(stringValue(A, 3), is(equalTo("Generated as on " + generatedDateTimeValue)));
        assertThat(stringValue(A, 5), is(equalTo("callId")));
        assertThat(stringValue(B, 5), is(equalTo("providerId")));
        assertThat(stringValue(C, 5), is(equalTo("district")));
        assertThat(stringValue(D, 5), is(equalTo("reminderType")));
        assertThat(stringValue(E, 5), is(Wednesday.name()));
        assertThat(numericValue(F, 5), is(41619.0));
        assertThat(numericValue(G, 5), is(41621.0));
        assertThat(stringValue(H, 5), is(equalTo("Yes")));
        assertThat(numericValue(I, 5), is(new Double(3)));
        assertThat(stringValue(J, 5), is(equalTo("Yes")));
        assertThat(stringValue(K, 5), is(equalTo("type")));
        assertThat(numericValue(L, 5), is(new Double(1)));
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
