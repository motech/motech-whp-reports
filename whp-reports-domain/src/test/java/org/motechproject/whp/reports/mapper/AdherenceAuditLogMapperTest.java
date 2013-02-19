package org.motechproject.whp.reports.mapper;

import org.joda.time.DateTime;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;

public class AdherenceAuditLogMapperTest {

    AdherenceAuditLogMapper adherenceAuditLogMapper;
/*

    @Test
    public void shouldMapAdherenceAuditLogDTOToAdherenceAuditLog() {
        AdherenceAuditLogDTO adherenceAuditLogDTO = new AdherenceAuditLogDTO();
      //  AdherenceAuditLog adherenceAuditLog = new AdherenceAuditLog();

        createAdherenceAuditLog(adherenceAuditLogDTO);
        adherenceAuditLogMapper.map(adherenceAuditLogDTO, adherenceAuditLog);

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
*/

    private void createAdherenceAuditLog(AdherenceAuditLogDTO adherenceAuditLogDTO) {
        adherenceAuditLogDTO.setPatientId("patientId");
        adherenceAuditLogDTO.setProviderId("providerId");
        adherenceAuditLogDTO.setCreationTime(new DateTime());
        adherenceAuditLogDTO.setDoseDate(new DateTime());
        adherenceAuditLogDTO.setNumberOfDosesTaken(2);
        adherenceAuditLogDTO.setPillStatus("pillStatus");
        adherenceAuditLogDTO.setSourceOfChange("channel");
        adherenceAuditLogDTO.setTbId("tbId");
        adherenceAuditLogDTO.setUserId("userId");
    }
}
