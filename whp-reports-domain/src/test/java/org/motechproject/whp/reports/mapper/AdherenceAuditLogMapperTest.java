package org.motechproject.whp.reports.mapper;

import org.junit.Test;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

public class AdherenceAuditLogMapperTest {

    AdherenceAuditLogMapper adherenceAuditLogMapper = new AdherenceAuditLogMapper();

    @Test
    public void shouldMapAdherenceAuditLogDTOToAdherenceAuditLog() {
        AdherenceAuditLogDTO adherenceAuditLogDTO = new AdherenceAuditLogDTO();

        createAdherenceAuditLog(adherenceAuditLogDTO);
        AdherenceAuditLog adherenceAuditLog = adherenceAuditLogMapper.map(adherenceAuditLogDTO);

        assertEquals(adherenceAuditLogDTO.getCreationTime(), adherenceAuditLog.getCreationTime());
        assertEquals(adherenceAuditLogDTO.getUserId(), adherenceAuditLog.getUserId());
        assertEquals(adherenceAuditLogDTO.getPatientId(), adherenceAuditLog.getPatientId());
        assertEquals(adherenceAuditLogDTO.getProviderId(), adherenceAuditLog.getProviderId());
        assertEquals(adherenceAuditLogDTO.getTbId(), adherenceAuditLog.getTbId());
        assertEquals(adherenceAuditLogDTO.getDoseDate(), adherenceAuditLog.getDoseDate());
        assertEquals(adherenceAuditLogDTO.getNumberOfDosesTaken(), adherenceAuditLog.getNumberOfDosesTaken());
        assertEquals(adherenceAuditLogDTO.getPillStatus(), adherenceAuditLog.getPillStatus());
        assertEquals(adherenceAuditLogDTO.getNumberOfDosesTaken(), adherenceAuditLog.getNumberOfDosesTaken());

    }

    private void createAdherenceAuditLog(AdherenceAuditLogDTO adherenceAuditLogDTO) {
        adherenceAuditLogDTO.setPatientId("patientId");
        adherenceAuditLogDTO.setProviderId("providerId");
        long currentTime = System.currentTimeMillis();
        adherenceAuditLogDTO.setCreationTime(new Timestamp(currentTime));
        adherenceAuditLogDTO.setDoseDate(new Date(currentTime));
        adherenceAuditLogDTO.setNumberOfDosesTaken(2);
        adherenceAuditLogDTO.setPillStatus("pillStatus");
        adherenceAuditLogDTO.setChannel("channel");
        adherenceAuditLogDTO.setTbId("tbId");
        adherenceAuditLogDTO.setUserId("userId");
    }
}
