package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AllCallLogs;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallLogServiceTest {

    @Mock
    private AllCallLogs allCallLogs;
    private CallLogService callLogService;

    @Before
    public void setUp() {
        initMocks(this);
        callLogService = new CallLogService(allCallLogs);
    }

    @Test
    public void shouldSaveCallLog() {
        AdherenceCallLog log = new AdherenceCallLog();
        callLogService.save(log);
        verify(allCallLogs).save(log);
    }
}
