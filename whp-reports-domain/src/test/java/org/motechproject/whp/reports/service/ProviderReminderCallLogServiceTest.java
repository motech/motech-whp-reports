package org.motechproject.whp.reports.service;


import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.AdherenceSubmissionRequest;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.MostRecentProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.ProviderReminderCallLogPageRequest;
import org.motechproject.whp.reports.repository.ProviderReminderCallLogRepository;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProviderReminderCallLogServiceTest {

    @Mock
    ProviderReminderCallLogRepository providerReminderCallLogRepository;

    ProviderReminderCallLogService providerReminderCallLogService;
    private final Date submissionDate = new Date();
    private final String providerId = "providerId";

    @Before
    public void setUp() {
        initMocks(this);
        providerReminderCallLogService = new ProviderReminderCallLogService(providerReminderCallLogRepository);
    }

    @Test
    public void shouldSaveProviderReminderCallLog() {
        ProviderReminderCallLog providerReminderCallLog = new ProviderReminderCallLog();
        providerReminderCallLogService.save(providerReminderCallLog);
        verify(providerReminderCallLogRepository).save(providerReminderCallLog);
    }

    @Test
    public void shouldFetchMostRecentReminderForGivenProviderIdAndAttemptDate() {
        ProviderReminderCallLog expectedCallLog = new ProviderReminderCallLog();

        Timestamp attemptTime = new Timestamp(System.currentTimeMillis());
        String providerId = "provider";
        when(providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, attemptTime, new MostRecentProviderReminderCallLog())).thenReturn(asList(expectedCallLog));

        ProviderReminderCallLog callLog = providerReminderCallLogService.getMostRecentLogForProvider(providerId, attemptTime);

        assertEquals(expectedCallLog, callLog);
    }

    @Test
    public void shouldReturnNullIfThereIsNoReminderForGivenProviderIdAndAttemptDate() {
        Timestamp attemptTime = new Timestamp(System.currentTimeMillis());
        String providerId = "provider";
        when(providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, attemptTime, new MostRecentProviderReminderCallLog())).thenReturn(new ArrayList<ProviderReminderCallLog>());

        ProviderReminderCallLog callLog = providerReminderCallLogService.getMostRecentLogForProvider(providerId, attemptTime);

        assertNull(callLog);
    }

    @Test
    public void shouldUpdateReminderForGivenAdherenceSubmissionIfWithinAdherenceWindow() {
        AdherenceSubmissionRequest adherenceSubmissionRequest = createAdherenceSubmissionRequest(submissionDate, providerId, true);

        ProviderReminderCallLog callLog = new ProviderReminderCallLog();
        callLog.setProviderId(providerId);

        when(providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, new Timestamp(submissionDate.getTime()), new MostRecentProviderReminderCallLog())).thenReturn(asList(callLog));

        providerReminderCallLogService.updateAdherenceStatus(adherenceSubmissionRequest);

        ArgumentCaptor<ProviderReminderCallLog> callLogArgumentCaptor = ArgumentCaptor.forClass(ProviderReminderCallLog.class);
        verify(providerReminderCallLogRepository).save(callLogArgumentCaptor.capture());

        assertTrue(callLogArgumentCaptor.getValue().getAdherenceReported());
    }

    @Test
    public void shouldNotUpdateReminderForGivenAdherenceSubmissionIfOutsideAdherenceWindow() {
        AdherenceSubmissionRequest adherenceSubmissionRequest = createAdherenceSubmissionRequest(submissionDate, providerId, false);

        providerReminderCallLogService.updateAdherenceStatus(adherenceSubmissionRequest);

        verifyZeroInteractions(providerReminderCallLogRepository);
   }

    private AdherenceSubmissionRequest createAdherenceSubmissionRequest(Date submissionDate, String providerId, boolean withinAdherenceWindow) {
        AdherenceSubmissionRequest adherenceSubmissionRequest = new AdherenceSubmissionRequest();
        adherenceSubmissionRequest.setProviderId(providerId);
        adherenceSubmissionRequest.setSubmittedBy(providerId);
        adherenceSubmissionRequest.setWithinAdherenceWindow(withinAdherenceWindow);
        adherenceSubmissionRequest.setSubmissionDate(submissionDate);
        return adherenceSubmissionRequest;
    }

    @Test
    public void shouldHandleNullReminderCallLogWhileUpdatingAdherenceStatus() {
        AdherenceSubmissionRequest request = createAdherenceSubmissionRequest(submissionDate, providerId, true);
        request.setWithinAdherenceWindow(true);

        when(providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, new Timestamp(submissionDate.getTime()), new MostRecentProviderReminderCallLog())).thenReturn(new ArrayList<ProviderReminderCallLog>());

        providerReminderCallLogService.updateAdherenceStatus(request);

        verify(providerReminderCallLogRepository).findByProviderIdAndAttemptTimeLessThan(providerId, new Timestamp(submissionDate.getTime()), new MostRecentProviderReminderCallLog());
        verifyNoMoreInteractions(providerReminderCallLogRepository);
    }

    @Test
    public void shouldPageReminderCallLogs() {
        List<ProviderReminderCallLog> callLogList1 = mock(List.class);
        List<ProviderReminderCallLog> callLogList2 = mock(List.class);

        Page<ProviderReminderCallLog> page1 = mock(Page.class);
        when(page1.getContent()).thenReturn(callLogList1);

        Page<ProviderReminderCallLog> page2 = mock(Page.class);
        when(page2.getContent()).thenReturn(callLogList2);

        ProviderReminderCallLogPageRequest pageRequest1 = new ProviderReminderCallLogPageRequest(1, 3);
        ProviderReminderCallLogPageRequest pageRequest2 = new ProviderReminderCallLogPageRequest(2, 3);

        when(providerReminderCallLogRepository.findAll(pageRequest1)).thenReturn(page1);
        when(providerReminderCallLogRepository.findAll(pageRequest2)).thenReturn(page2);

        Assert.assertEquals(callLogList1, providerReminderCallLogService.getAll(1, 3));
        Assert.assertEquals(callLogList2, providerReminderCallLogService.getAll(2, 3));

        verify(providerReminderCallLogRepository).findAll(pageRequest1);
        verify(providerReminderCallLogRepository).findAll(pageRequest2);
        verify(page1).getContent();
        verify(page2).getContent();
    }


}
