package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AllAdherenceCallLogs;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class AdherenceCallLogServiceTest {

    @Mock
    private AllAdherenceCallLogs allCallLogs;
    private AdherenceCallLogService callLogService;

    @Before
    public void setUp() {
        initMocks(this);
        callLogService = new AdherenceCallLogService(allCallLogs);
    }

    @Test
    public void shouldSaveCallLog() {
        AdherenceCallLog log = new AdherenceCallLog();
        callLogService.save(log);
        verify(allCallLogs).save(log);
    }
}
