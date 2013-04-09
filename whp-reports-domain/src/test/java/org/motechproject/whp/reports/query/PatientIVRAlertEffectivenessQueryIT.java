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
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.domain.TreatmentWeek;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.repository.AdherenceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;
import static org.motechproject.whp.reports.date.WHPDateTime.toSqlTimestamp;

public class PatientIVRAlertEffectivenessQueryIT extends IntegrationTest{

    @Autowired
    AdherenceRecordRepository adherenceRecordRepository;

    @Autowired
    GenericCallLogRepository genericCallLogRepository;

    @Autowired
    BigQueryService queryService;

    @Test
    public void shouldCountNumberOfPatientsCalledAndGivenAdherenceEveryWeek() {

        LocalDate threeWeeksAgo = DateUtil.today().minusWeeks(3);
        LocalDate twoWeeksAgo = DateUtil.today().minusWeeks(2);
        LocalDate oneWeeksAgo = DateUtil.today().minusWeeks(1);

        DateTime threeWeeksAgoTimestamp = DateUtil.now().minusWeeks(3);
        DateTime twoWeeksAgoTimestamp = DateUtil.now().minusWeeks(2);
        DateTime oneWeeksAgoTimestamp = DateUtil.now().minusWeeks(1);
        String patientId1 = "patientId1";
        String patientId2 = "patientId2";

        createAdherenceRecord(patientId1, threeWeeksAgo, "Taken");
        createAdherenceRecord(patientId1, twoWeeksAgo, "Taken");
        createAdherenceRecord(patientId2, twoWeeksAgo, "Taken");

        createCallLog(threeWeeksAgoTimestamp, patientId1);
        createCallLog(twoWeeksAgoTimestamp, patientId1);
        createCallLog(twoWeeksAgoTimestamp, patientId2);
        createCallLog(oneWeeksAgoTimestamp, patientId1);
        createCallLog(oneWeeksAgoTimestamp, patientId2);

        QueryResult queryResult = queryService.executeQuery("patient.ivrAlerts.effectiveness", new FilterParams());
        List<Map<String,Object>> result = queryResult.getContent();


        assertResult(result, 0, 1L, 1L, threeWeeksAgo);
        assertResult(result, 1, 2L, 2L, twoWeeksAgo);
        assertResult(result, 2, 2L, 0L, oneWeeksAgo);
    }

    private void assertResult(List<Map<String, Object>> result, int index, long patientsWithIvrCalls, long patientsWithAdherenceGiven, LocalDate callDate) {
        assertThat((Long)result.get(index).get("patient_with_ivr_calls"), is(patientsWithIvrCalls));
        assertThat((Long) result.get(index).get("patients_with_adherence_given"), is(patientsWithAdherenceGiven));
        assertThat((Date) result.get(index).get("call_week_end_date"), is(WHPDate.toSqlDate(new TreatmentWeek(callDate).endDate())));
    }

    private CallLog createCallLog(DateTime callAttemptDateTime, String patientId1) {
        CallLog callLog = new CallLogBuilder().withDefaults().withAttemptDateTime(toSqlTimestamp(callAttemptDateTime)).withCustomData("patientId", patientId1).build();
        genericCallLogRepository.save(callLog);
        return callLog;
    }

    private AdherenceRecord createAdherenceRecord(String patientId, LocalDate pillDate, String pillStatus) {
        AdherenceRecord adherenceRecord = new AdherenceRecord();
        adherenceRecord.setDistrict("district");
        adherenceRecord.setPatientId(patientId);
        adherenceRecord.setPillStatus(pillStatus);
        adherenceRecord.setProviderId("providerId");
        adherenceRecord.setTbId("tbId");
        adherenceRecord.setTherapyId("therapyId");
        adherenceRecord.setPillDate(toSqlDate(pillDate));

        adherenceRecordRepository.save(adherenceRecord);
        return adherenceRecord;
    }

    @After
    public void tearDown() {
        adherenceRecordRepository.deleteAll();
        genericCallLogRepository.deleteAll();
    }
}

