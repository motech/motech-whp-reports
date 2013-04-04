package org.motechproject.whp.reports.bigquery.dao;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.motechproject.util.DateUtil;
import org.motechproject.whp.reports.bigquery.model.FilterParams;
import org.motechproject.whp.reports.bigquery.response.QueryResult;
import org.motechproject.whp.reports.bigquery.service.Queries;
import org.motechproject.whp.reports.date.WHPDate;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.repository.AdherenceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.motechproject.whp.reports.date.WHPDate.toSqlDate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-applicationReportingBigQueryContext.xml")
public class BigQueryDAOIT {

    @Autowired
    BigQueryDAO bigQueryDAO;

    @Autowired
    Queries queries;

    @Autowired
    AdherenceRecordRepository adherenceRecordRepository;

    @Test
    public void shouldExecuteQueryWithFilterParams() {
        createAdherenceRecord("patient1", DateUtil.today(), "Taken");
        createAdherenceRecord("patient1", DateUtil.today().plusDays(1), "Taken");
        createAdherenceRecord("patient1", DateUtil.today().minusDays(1), "Taken");
        createAdherenceRecord("patient2", DateUtil.today(), "Taken");
        createAdherenceRecord("patient3", DateUtil.today(), "NotTaken");


        FilterParams filterParams = new FilterParams();
        filterParams.put("pillStatus", "Taken");
        filterParams.put("pillDate_date", DateUtil.today().toString("dd/MM/yyyy"));

        String query = "select count(1) as count_of_doses, patient_id from whp_reports.adherence_record " +
                "where pill_status = :pillStatus and pill_date = :pillDate_date group by patient_id order by patient_id";

        QueryResult queryResult = bigQueryDAO.executeQuery(query, filterParams);
        assertEquals(2, queryResult.getContent().size());
        assertEquals(1L, queryResult.getContent().get(0).get("count_of_doses"));
        assertEquals("patient1", queryResult.getContent().get(0).get("patient_id"));
        assertEquals(1L, queryResult.getContent().get(1).get("count_of_doses"));
        assertEquals("patient2", queryResult.getContent().get(1).get("patient_id"));
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
}
