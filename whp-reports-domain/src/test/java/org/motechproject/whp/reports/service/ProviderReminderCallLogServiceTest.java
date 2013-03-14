package org.motechproject.whp.reports.service;


import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.motechproject.whp.reports.contract.AdherenceSubmissionRequest;
import org.motechproject.whp.reports.contract.enums.YesNo;
import org.motechproject.whp.reports.domain.measure.calllog.ProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.MostRecentProviderReminderCallLog;
import org.motechproject.whp.reports.repository.ProviderReminderCallLogRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
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

        assertThat(callLogArgumentCaptor.getValue().getAdherenceReported(), is(YesNo.Yes.code()));
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
}
