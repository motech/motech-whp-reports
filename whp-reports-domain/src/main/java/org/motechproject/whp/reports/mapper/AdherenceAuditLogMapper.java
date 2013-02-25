package org.motechproject.whp.reports.mapper;

import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.springframework.stereotype.Component;

@Component
public class AdherenceAuditLogMapper {


    public AdherenceAuditLog map(AdherenceAuditLogDTO adherenceAuditLogDTO) {
        AdherenceAuditLog adherenceAuditLog = new AdherenceAuditLog();

        adherenceAuditLog.setPatientId(adherenceAuditLogDTO.getPatientId());
        adherenceAuditLog.setProviderId(adherenceAuditLogDTO.getProviderId());
        adherenceAuditLog.setChannel(adherenceAuditLogDTO.getChannel());
        adherenceAuditLog.setUserId(adherenceAuditLogDTO.getUserId());
        adherenceAuditLog.setTbId(adherenceAuditLogDTO.getTbId());
        adherenceAuditLog.setPillStatus(adherenceAuditLogDTO.getPillStatus());
        adherenceAuditLog.setDoseDate(adherenceAuditLogDTO.getDoseDate());
        adherenceAuditLog.setCreationTime(adherenceAuditLogDTO.getCreationTime());
        adherenceAuditLog.setNumberOfDosesTaken(adherenceAuditLogDTO.getNumberOfDosesTaken());
        adherenceAuditLog.setIsGivenByProvider(adherenceAuditLogDTO.getIsGivenByProvider());

        return adherenceAuditLog;
    }
}
