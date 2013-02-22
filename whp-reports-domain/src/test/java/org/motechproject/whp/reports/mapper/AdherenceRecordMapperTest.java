package org.motechproject.whp.reports.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.contract.adherence.AdherenceRecordDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceRecord;

import java.sql.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AdherenceRecordMapperTest {

    @Test
    public void shouldMapAdherenceDTOtoAdherenceRecord() {
        AdherenceRecordDTO adherenceRecordDTO = createAdherenceRecordDTO();

        AdherenceRecordMapper adherenceRecordMapper = new AdherenceRecordMapper();
        AdherenceRecord adherenceRecord = adherenceRecordMapper.map(adherenceRecordDTO);

        assertThat(adherenceRecord.getDistrict(), is(adherenceRecordDTO.getDistrict()));
        assertThat(adherenceRecord.getPatientId(), is(adherenceRecordDTO.getPatientId()));
        assertThat(adherenceRecord.getPillDate(), is(adherenceRecordDTO.getPillDate()));
        assertThat(adherenceRecord.getPillStatus(), is(adherenceRecordDTO.getPillStatus()));
        assertThat(adherenceRecord.getProviderId(), is(adherenceRecordDTO.getProviderId()));
        assertThat(adherenceRecord.getTbId(), is(adherenceRecordDTO.getTbId()));
        assertThat(adherenceRecord.getTherapyId(), is(adherenceRecordDTO.getTherapyId()));

    }

    private AdherenceRecordDTO createAdherenceRecordDTO() {
        AdherenceRecordDTO adherenceRecordDTO = new AdherenceRecordDTO();
        adherenceRecordDTO.setDistrict("district");
        adherenceRecordDTO.setPatientId("patientId");
        adherenceRecordDTO.setPillDate(new Date(System.currentTimeMillis()));
        adherenceRecordDTO.setPillStatus("Taken");
        adherenceRecordDTO.setProviderId("providerId");
        adherenceRecordDTO.setTbId("tbId");
        adherenceRecordDTO.setTherapyId("therapyId");
        return adherenceRecordDTO;
    }
}
