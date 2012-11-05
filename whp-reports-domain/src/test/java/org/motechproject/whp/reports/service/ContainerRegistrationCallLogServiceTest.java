package org.motechproject.whp.reports.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.AllContainerRegistrationCallLogs;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerRegistrationCallLogServiceTest {

    @Mock
    private AllContainerRegistrationCallLogs allContainerRegistrationCallLogs;
    private ContainerRegistrationCallLogService containerRegistrationCallLogService;

    @Before
    public void setUp() {
        initMocks(this);
        containerRegistrationCallLogService = new ContainerRegistrationCallLogService(allContainerRegistrationCallLogs);
    }

    @Test
    public void shouldSaveContainerRegistrationCallLog() {
        ContainerRegistrationCallLog containerRegistrationCallLog = new ContainerRegistrationCallLog();
        containerRegistrationCallLogService.save(containerRegistrationCallLog);
        verify(allContainerRegistrationCallLogs).save(containerRegistrationCallLog);
    }
}
