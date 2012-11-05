package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AdherenceCallLogRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceCallLogServiceTest {
    private AdherenceCallLogService callLogService;
    @Mock
    private AdherenceCallLogRepository adherenceCallLogRepository;

    @Before
    public void setUp() {
        initMocks(this);
        callLogService = new AdherenceCallLogService(adherenceCallLogRepository);
    }

    @Test
    public void shouldSaveCallLog() {
        AdherenceCallLog log = new AdherenceCallLog();
        callLogService.save(log);
        verify(adherenceCallLogRepository).save(log);
    }
}
