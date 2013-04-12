package org.motechproject.whp.reports.query;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
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
import org.motechproject.whp.reports.domain.patient.Therapy;
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

public class PatientAlertCallLogQueryIT extends IntegrationTest{

    @Autowired
    GenericCallLogRepository callLogRepository;
    @Autowired
    PatientRepository patientRepository;

    @Autowired
    BigQueryService bigQueryService;

    CallLog callLog1;
    CallLog callLog2;

    Patient patient;

    @Test
    public void shouldReturnPatientAlertCallLog(){
         callLog1 = new CallLogBuilder()
                .withDefaults()
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "2")
                .withCallEvents("tb_acknowledgement", "1")

                .build();
        long oneMinute=1*60*1000;
        callLog1.setEndDateTime(new Timestamp(callLog1.getStartDateTime().getTime() + oneMinute));
        DateTime attemptTime = new DateTime(2013, 4, 12, 0, 0, 0);
        callLog1.setAttemptTime(WHPDateTime.toSqlTimestamp(attemptTime));

        callLog2 = new CallLogBuilder()
                .withDefaults()
                .withCustomData("patient_id", "patientid")
                .withCustomData("adherence_missing_weeks", "4")
                .withCallEvents("tb_acknowledgement", "2")
                .build();
        callLog2.setCallId("callId2");
        callLog2.setEndDateTime(new Timestamp(callLog2.getStartDateTime().getTime() + oneMinute));
        callLog2.setAttemptTime(WHPDateTime.toSqlTimestamp(attemptTime.minusMonths(2)));

        patient = new PatientBuilder().withDefaults().build();
        Therapy currentTherapy = patient.getTherapies().get(0);
        List<Treatment> allTreatments = currentTherapy.getTreatments();
        Treatment treatment = setTreatmentWithAnotherProvider();
        allTreatments.add(treatment);
        currentTherapy.setTreatments(allTreatments);
        patient.setTherapies(asList(currentTherapy));

        Treatment currentTreatment = currentTherapy.getTreatments().get(0);
        currentTreatment.setStartDate(new Date(callLog2.getAttemptTime().getTime() - 24 * 60 * 60L));
        currentTreatment.setEndDate(new Date(callLog2.getAttemptTime().getTime() + 24 * 60 * 60L));
        patientRepository.save(patient);
        callLogRepository.save(asList(callLog1, callLog2));


        QueryResult queryResult = bigQueryService.executeQuery("patientAlertCallLog", new FilterParams());
        assertThat(queryResult.getContent().size(), is(2));

        Map<String,Object> firstRecentCallLog = queryResult.getContent().get(0);
        assertThat((String) firstRecentCallLog.get("call_id"), is(callLog1.getCallId()));
        assertThat((String) firstRecentCallLog.get("patient_id"), is(callLog1.getCustomData().get("patient_id")));
        assertThat((String) firstRecentCallLog.get("provider_id"), is(treatment.getProviderId()));
        assertThat((String) firstRecentCallLog.get("provider_district"), is(treatment.getProviderDistrict()));
        assertThat((Timestamp) firstRecentCallLog.get("call_start_time"), is(callLog1.getStartDateTime()));

        Double durationInSeconds = new BigDecimal(callLog1.getEndDateTime().getTime() - callLog1.getStartDateTime().getTime()).divide(new BigDecimal(1000)).doubleValue();
        assertThat((Double) firstRecentCallLog.get("duration"), is(durationInSeconds));
        assertThat((Timestamp) firstRecentCallLog.get("call_attempt_time"), is(callLog1.getAttemptTime()));
        assertThat((String) firstRecentCallLog.get("alert_day"), is("Friday"));
        assertThat((String) firstRecentCallLog.get("alert_listened"), is("YES"));
        assertThat((String) firstRecentCallLog.get("disconnection_type"), is(callLog1.getDisposition()));
        assertThat((String) firstRecentCallLog.get("error_message"), is(callLog1.getErrorMessage()));
        assertThat((String) firstRecentCallLog.get("call_attempt_number"), is(callLog1.getAttempt()));
        assertThat((String) firstRecentCallLog.get("adherence_missing_weeks"), is(callLog1.getCustomData().get("adherence_missing_weeks")));


        Map<String,Object> secondRecentCallLog = queryResult.getContent().get(1);
        assertThat((String) secondRecentCallLog.get("call_id"), is(callLog2.getCallId()));
        assertThat((String) secondRecentCallLog.get("patient_id"), is(callLog2.getCustomData().get("patient_id")));
        assertThat((String) secondRecentCallLog.get("provider_id"), is(currentTreatment.getProviderId()));
        assertThat((String) secondRecentCallLog.get("provider_district"), is(currentTreatment.getProviderDistrict()));
        assertThat((Timestamp) secondRecentCallLog.get("call_start_time"), is(callLog2.getStartDateTime()));

        durationInSeconds = (double) (callLog2.getEndDateTime().getTime() - callLog2.getStartDateTime().getTime())/1000;
        assertThat((Double) secondRecentCallLog.get("duration"), is(durationInSeconds));
        assertThat((Timestamp) secondRecentCallLog.get("call_attempt_time"), is(callLog2.getAttemptTime()));
        assertThat((String) secondRecentCallLog.get("alert_day"), is("Tuesday"));
        assertThat((String) secondRecentCallLog.get("alert_listened"), is("NO"));
        assertThat((String) secondRecentCallLog.get("disconnection_type"), is(callLog2.getDisposition()));
        assertThat((String) secondRecentCallLog.get("error_message"), is(callLog2.getErrorMessage()));
        assertThat((String) secondRecentCallLog.get("call_attempt_number"), is(callLog2.getAttempt()));
        assertThat((String) secondRecentCallLog.get("adherence_missing_weeks"), is(callLog2.getCustomData().get("adherence_missing_weeks")));

    }

    public static Treatment setTreatmentWithAnotherProvider() {
        Treatment treatment = new Treatment();
        treatment.setCurrentTreatment("N");
        treatment.setStartDate(new Date(new LocalDate().toDate().getTime()));
        treatment.setIsPaused("Y");
        treatment.setPatientType("type");
        treatment.setPausedDate(new Date(new LocalDate().toDate().getTime()));
        treatment.setPreTreatmentSmearTestResult("Positive");
        treatment.setPreTreatmentWeight(80.0);
        treatment.setProviderDistrict("Begusarai");
        treatment.setProviderId("provider_id2");
        treatment.setReasonsForPause("A reason to remember");
        treatment.setTbRegistrationNumber("tbRegNumber");
        treatment.setTotalPausedDuration(10);
        treatment.setTreatmentOutcome("treatment outcome");
        treatment.setCloseTreatmentRemarks("you are free my child");
        return treatment;
    }

    @After
    public void tearDown(){
        callLogRepository.deleteAll();
        patientRepository.deleteAll();
    }
}
