package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.adherence.AdherenceAuditLogDTO;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.mapper.AdherenceAuditLogMapper;
import org.motechproject.whp.reports.repository.AdherenceAuditLogRepository;

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
}
