package org.motechproject.whp.reports.query;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;
import org.motechproject.bigquery.model.FilterParams;
import org.motechproject.bigquery.response.QueryResult;
import org.motechproject.bigquery.service.BigQueryService;
import org.motechproject.calllog.builder.CallLogBuilder;
import org.motechproject.calllog.domain.CallLog;
import org.motechproject.calllog.repository.GenericCallLogRepository;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.builder.PatientBuilder;
import org.motechproject.whp.reports.date.WHPDateTime;
import org.motechproject.whp.reports.domain.patient.Patient;
import org.motechproject.whp.reports.domain.patient.Treatment;
import org.motechproject.whp.reports.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlTimestamp;

public class PatientAlertCallLogQueryIT extends IntegrationTest{

    @Autowired
    GenericCallLogRepository callLogRepository;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    BigQueryService bigQueryService;

    @Test
    public void shouldReturnCallLogSummary() {
        DateTime treatmentStartDate = new DateTime(2013, 4, 12, 0, 0, 0);
        Patient patient = new PatientBuilder().withDefaults()
                .withTreatmentStartDate(getRelativeDate(treatmentStartDate, 0))
                .withTreatmentEndDate(null)
                .build();

        DateTime callLog1AttemptTime = new DateTime(getRelativeDate(treatmentStartDate, 5).getTime());
        CallLog callLogForAnsweredCall = new CallLogBuilder()
                .withDefaults()
                .withCallId("call1")
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "2")
                .withAttemptDateTime(toSqlTimestamp(callLog1AttemptTime))
                .withStartDateTime(toSqlTimestamp(callLog1AttemptTime.plusSeconds(5)))
                .withEndDateTime(toSqlTimestamp(callLog1AttemptTime.plusMinutes(5)))
                .build();

        DateTime notAnsweredCallAttemptTime = callLog1AttemptTime.plusDays(1);
        CallLog callLogForNotAnsweredCall = new CallLogBuilder()
                .withDefaults()
                .withCallId("call2")
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "2")
                .withCallEvents("tb_acknowledgement", "1")
                .withAttemptDateTime(toSqlTimestamp(notAnsweredCallAttemptTime))
                .withStartDateTime(toSqlTimestamp(notAnsweredCallAttemptTime.plusSeconds(5)))
                .withEndDateTime(toSqlTimestamp(notAnsweredCallAttemptTime.plusMinutes(5)))
                .build();

        DateTime invalidTbAcknowledgementAttemptTime = callLog1AttemptTime.plusDays(2);
        CallLog callLogWithInvalidTbAcknowledgement = new CallLogBuilder()
                .withDefaults()
                .withCallId("call3")
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "2")
                .withCallEvents("tb_acknowledgement", "3")
                .withAttemptDateTime(toSqlTimestamp(invalidTbAcknowledgementAttemptTime))
                .withStartDateTime(toSqlTimestamp(invalidTbAcknowledgementAttemptTime.plusSeconds(5)))
                .withEndDateTime(toSqlTimestamp(invalidTbAcknowledgementAttemptTime.plusMinutes(5)))
                .build();

        patientRepository.save(patient);
        callLogRepository.save(asList(callLogForAnsweredCall, callLogForNotAnsweredCall, callLogWithInvalidTbAcknowledgement));

        QueryResult queryResult = bigQueryService.executeQuery("patientAlertCallLog", new FilterParams());
        List<Map<String, Object>> actualCallLogs = queryResult.getContent();
        assertThat(actualCallLogs.size(), is(3));

        Treatment treatment = patient.getTherapies().get(0).getTreatments().get(0);
        assertPatientAlertCallLog(callLogWithInvalidTbAcknowledgement, treatment, actualCallLogs.get(0));
        assertPatientAlertCallLog(callLogForNotAnsweredCall, treatment, actualCallLogs.get(1));
        assertPatientAlertCallLog(callLogForAnsweredCall, treatment, actualCallLogs.get(2));
    }

    @Test
    public void shouldReturnPatientAlertCallLogWhenPatientHasBeenTransferredOutToAnotherProvider(){
        DateTime currentTreatmentCallAttemptTime = new DateTime(2013, 4, 12, 0, 0, 0);
        CallLog callLogForCurrentTreatment = new CallLogBuilder()
                .withDefaults()
                .withCallId("call2")
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "2")
                .withCallEvents("tb_acknowledgement", "1")
                .withAttemptDateTime(toSqlTimestamp(currentTreatmentCallAttemptTime))
                .withStartDateTime(toSqlTimestamp(currentTreatmentCallAttemptTime.plusSeconds(5)))
                .withEndDateTime(toSqlTimestamp(currentTreatmentCallAttemptTime.plusMinutes(5)))
                .build();

        DateTime previousTreatmentCallAttemptTime = currentTreatmentCallAttemptTime.minusMonths(2);
        CallLog callLogForPreviousTreatment = new CallLogBuilder()
                .withDefaults()
                .withCallId("call1")
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "4")
                .withCallEvents("tb_acknowledgement", "2")
                .withAttemptDateTime(toSqlTimestamp(previousTreatmentCallAttemptTime))
                .withStartDateTime(toSqlTimestamp(previousTreatmentCallAttemptTime.plusSeconds(5)))
                .withEndDateTime(toSqlTimestamp(previousTreatmentCallAttemptTime.plusMinutes(5)))
                .build();

        Patient patient = new PatientBuilder().withDefaults()
                .withTreatmentStartDate(getRelativeDate(previousTreatmentCallAttemptTime, -1))
                .withTreatmentEndDate(getRelativeDate(previousTreatmentCallAttemptTime, 1))
                .addTreatment(createOpenTreatmentWithProvider("provider_id2", getRelativeDate(currentTreatmentCallAttemptTime, -1)))
                .build();

        List<Treatment> treatments = patient.getTherapies().get(0).getTreatments();
        Treatment previousTreatment = treatments.get(0);
        Treatment currentTreatment = treatments.get(1);

        patientRepository.save(patient);
        callLogRepository.save(asList(callLogForCurrentTreatment, callLogForPreviousTreatment));

        QueryResult queryResult = bigQueryService.executeQuery("patientAlertCallLog", new FilterParams());
        assertThat(queryResult.getContent().size(), is(2));

        Map<String,Object> callLogResult1 = queryResult.getContent().get(0);
        assertPatientAlertCallLog(callLogForCurrentTreatment, currentTreatment, callLogResult1);

        Map<String,Object> callLogResult2 = queryResult.getContent().get(1);
        assertPatientAlertCallLog(callLogForPreviousTreatment, previousTreatment, callLogResult2);
    }

    private Date getRelativeDate(DateTime attemptTime, int days) {
        return WHPDateTime.toSqlDate(attemptTime.plusDays(days));
    }


    private void assertPatientAlertCallLog(CallLog calLog, Treatment treatment, Map<String, Object> callLog) {
        assertThat((String) callLog.get("call_id"), is(calLog.getCallId()));
        assertThat((String) callLog.get("patient_id"), is(calLog.getCustomData().get("patient_id")));
        assertThat((String) callLog.get("provider_id"), is(treatment.getProviderId()));
        assertThat((String) callLog.get("provider_district"), is(treatment.getProviderDistrict()));
        assertThat((Timestamp) callLog.get("call_start_time"), is(calLog.getStartDateTime()));

        Double durationInSeconds = new BigDecimal(calLog.getEndDateTime().getTime() - calLog.getStartDateTime().getTime()).divide(new BigDecimal(1000)).doubleValue();
        assertThat((Double) callLog.get("duration"), is(durationInSeconds));
        assertThat((Timestamp) callLog.get("call_attempt_time"), is(calLog.getAttemptTime()));
        assertThat((String) callLog.get("alert_day"), is(WHPDateTime.dayOfWeek(calLog.getAttemptTime())));

        String tb_acknowledgement = calLog.getCallEvents().get("tb_acknowledgement");
        assertThat((String) callLog.get("alert_listened"), is(getExpectedTbAcknowledgement(tb_acknowledgement)));
        assertThat((String) callLog.get("disconnection_type"), is(calLog.getDisposition()));
        assertThat((String) callLog.get("error_message"), is(calLog.getErrorMessage()));
        assertThat((String) callLog.get("call_attempt_number"), is(calLog.getAttempt()));
        assertThat((String) callLog.get("adherence_missing_weeks"), is(calLog.getCustomData().get("adherence_missing_weeks")));
    }

    private String getExpectedTbAcknowledgement(String tb_acknowledgement) {
        return "1".equals(tb_acknowledgement) ? "YES" : "2".equals(tb_acknowledgement) ? "NO" : null;
    }

    public Treatment createOpenTreatmentWithProvider(String providerId, Date treatmentStartDate) {
        Treatment treatment = PatientBuilder.defaultTreatment();
        treatment.setStartDate(treatmentStartDate);
        treatment.setEndDate(null);
        treatment.setProviderId(providerId);
        return treatment;
    }

    @After
    public void tearDown(){
        callLogRepository.deleteAll();
        patientRepository.deleteAll();
    }
}
