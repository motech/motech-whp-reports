package org.motechproject.whp.reports.calllog.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.whp.reports.calllog.domain.CallLog;
import org.motechproject.whp.reports.calllog.mapper.CallLogMapper;
import org.motechproject.whp.reports.calllog.repository.GenericCallLogRepository;
import org.motechproject.whp.reports.calllog.request.CallLogRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CallLogServiceTest {

    private CallLogService callLogService;

    @Mock
    private GenericCallLogRepository genericCallLogRepository;
    @Mock
    private CallLogMapper callLogMapper;

    @Before
    public void setUp() {
        initMocks(this);
        callLogService = new CallLogService(callLogMapper, genericCallLogRepository);
    }

    @Test
    public void shouldAddCallLog() {
        CallLogRequest callLogRequest = new CallLogRequest();
        CallLog callLog = mock(CallLog.class);
        when(callLogMapper.map(callLogRequest)).thenReturn(callLog);

        callLogService.add(callLogRequest);

        verify(callLogMapper).map(callLogRequest);
        verify(genericCallLogRepository).save(callLog);
    }
}
