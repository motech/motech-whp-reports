package org.motechproject.whp.reports.repository;

import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecordId;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AdherenceRecordRepositoryIT extends IntegrationTest{

    @Autowired
    AdherenceRecordRepository adherenceRecordRepository;

    @Test
    public void shouldAddAndUpdateAdherenceRecord() {
        AdherenceRecord adherenceRecord = createDefaultAdherenceRecord();
        adherenceRecordRepository.save(adherenceRecord);

        AdherenceRecordId adherenceRecordId = adherenceRecord.getAdherenceRecordId();
        assertNotNull(adherenceRecordRepository.findOne(adherenceRecordId));

        String newDistrict = "newDistrict";
        adherenceRecord.setDistrict(newDistrict);
        adherenceRecordRepository.save(adherenceRecord);

        assertEquals(newDistrict, adherenceRecordRepository.findOne(adherenceRecordId).getDistrict());
    }

    @Test
    public void shouldAddAdherenceRecordForNewIds() {
        AdherenceRecord adherenceRecord = createDefaultAdherenceRecord();
        adherenceRecordRepository.save(adherenceRecord);

        AdherenceRecordId adherenceRecordId = adherenceRecord.getAdherenceRecordId();
        assertNotNull(adherenceRecordRepository.findOne(adherenceRecordId));

        adherenceRecord.setPatientId("newPatientId");

        adherenceRecordRepository.save(adherenceRecord);
        assertNotNull(adherenceRecordRepository.findOne(adherenceRecordId));

        AdherenceRecordId newAdherenceRecordId =adherenceRecord.getAdherenceRecordId();

        assertNotNull(adherenceRecordRepository.findOne(newAdherenceRecordId));

    }

    private AdherenceRecord createDefaultAdherenceRecord() {
        AdherenceRecord adherenceRecord = new AdherenceRecord();
        adherenceRecord.setDistrict("district");
        adherenceRecord.setPatientId("patientId");
        adherenceRecord.setPillStatus("pillStatus");
        adherenceRecord.setProviderId("providerId");
        adherenceRecord.setTbId("tbId");
        adherenceRecord.setTherapyId("therapyId");
        adherenceRecord.setPillDate(new Date(System.currentTimeMillis()));
        return adherenceRecord;
    }

    @After
    public void tearDown() {
        adherenceRecordRepository.deleteAll();
    }
}
