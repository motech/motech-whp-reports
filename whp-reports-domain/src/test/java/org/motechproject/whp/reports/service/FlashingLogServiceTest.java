package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.repository.FlashingLogRepository;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class FlashingLogServiceTest {

    @Mock
    private FlashingLogRepository flashingLogRepository;
    private FlashingLogService flashingLogService;

    @Before
    public void setUp() {
        initMocks(this);
        flashingLogService = new FlashingLogService(flashingLogRepository);
    }

    @Test
    public void shouldSaveCallLog() {
        FlashingLog log = new FlashingLog();
        flashingLogService.save(log);
        verify(flashingLogRepository).save(log);
    }
}
