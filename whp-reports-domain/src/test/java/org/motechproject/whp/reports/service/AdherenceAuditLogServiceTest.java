package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.mapper.AdherenceAuditLogMapper;
import org.motechproject.whp.reports.repository.AdherenceAuditLogRepository;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceAuditLogServiceTest {
    @Mock
    AdherenceAuditLogRepository adherenceAuditLogRepository;
    @Mock
    AdherenceAuditLogMapper adherenceAuditLogMapper;

    AdherenceAuditLogService adherenceAuditLogService;

    @Before
    public void setUp() {
        initMocks(this);
        adherenceAuditLogService = new AdherenceAuditLogService(adherenceAuditLogMapper, adherenceAuditLogRepository);
    }

    @Test
    public void shouldMapAndSaveAdherenceAuditLog() {
        AdherenceAuditLogDTO adherenceAuditLogDTO = mock(AdherenceAuditLogDTO.class);
        AdherenceAuditLog adherenceAuditLog = mock(AdherenceAuditLog.class);

        when(adherenceAuditLogMapper.map(adherenceAuditLogDTO)).thenReturn(adherenceAuditLog);

        adherenceAuditLogService.save(adherenceAuditLogDTO);

        verify(adherenceAuditLogRepository).save(adherenceAuditLog);
    }

    @Test
    public void shouldMapAndDeleteAdherenceAuditLog() {
        AdherenceAuditLogDTO adherenceAuditLogDTO = mock(AdherenceAuditLogDTO.class);
        AdherenceAuditLog adherenceAuditLog = mock(AdherenceAuditLog.class);
        List<AdherenceAuditLog> auditLogs = new ArrayList<>();
        auditLogs.add(adherenceAuditLog);
        List<AdherenceAuditLogDTO> adherenceAuditLogDTOs = new ArrayList<>();
        adherenceAuditLogDTOs.add(adherenceAuditLogDTO);

        when(adherenceAuditLogRepository.findByPatientIdAndTbId(adherenceAuditLogDTO.getPatientId(), adherenceAuditLogDTO.getTbId())).thenReturn(auditLogs);

        adherenceAuditLogService.delete(adherenceAuditLogDTOs);

        verify(adherenceAuditLogRepository).findByPatientIdAndTbId(adherenceAuditLogDTO.getPatientId(),adherenceAuditLogDTO.getTbId());
        verify(adherenceAuditLogRepository).delete(auditLogs);
    }
}
