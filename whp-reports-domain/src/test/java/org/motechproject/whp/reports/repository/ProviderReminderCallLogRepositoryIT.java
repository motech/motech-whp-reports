package org.motechproject.whp.reports.repository;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.motechproject.whp.reports.domain.paging.MostRecentProviderReminderCallLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProviderReminderCallLogRepositoryIT extends IntegrationTest {

    @Autowired
    private ProviderReminderCallLogRepository providerReminderCallLogRepository;

    @Test
    public void shouldCreateCallLog() {
        String providerId = "providerId";

        ProviderReminderCallLog providerReminderCallLog = createReminderCallLog(providerId);

        providerReminderCallLogRepository.save(providerReminderCallLog);

        assertNotNull(providerReminderCallLog.getId());
        assertEquals(providerReminderCallLog, providerReminderCallLogRepository.findOne(providerReminderCallLog.getId()));
    }

    private ProviderReminderCallLog createReminderCallLog(String providerId) {
        ProviderReminderCallLog providerReminderCallLog = new ProviderReminderCallLog();

        providerReminderCallLog.setCallId(UUID.randomUUID().toString().substring(0, 32));
        providerReminderCallLog.setRequestId(UUID.randomUUID().toString().substring(0, 32));
        providerReminderCallLog.setDisconnectionType("disconnectionType");
        providerReminderCallLog.setAttempt(2);
        providerReminderCallLog.setEndTime(new Timestamp(1000));
        providerReminderCallLog.setStartTime(new Timestamp(0));
        providerReminderCallLog.setAttemptTime(new Timestamp(100));
        providerReminderCallLog.setMobileNumber("1234567890");
        providerReminderCallLog.setReminderType(ReminderType.ADHERENCE_NOT_REPORTED.name());
        providerReminderCallLog.setDisconnectionType(ReminderDisconnectionType.CALL_COMPLETE.name());
        providerReminderCallLog.setProviderId(providerId);
        return providerReminderCallLog;
    }

    @Test
    public void shouldReturnLastReminderSentToProvider() {

        Timestamp yesterday = new Timestamp(new DateTime().minusDays(1).toDate().getTime());
        Timestamp today = new Timestamp(new DateTime().toDate().getTime());

        String providerId = "provider1";
        ProviderReminderCallLog olderCallLog = createReminderCallLog(providerId);
        olderCallLog.setAttemptTime(yesterday);

        ProviderReminderCallLog newerCallLog = createReminderCallLog(providerId);
        newerCallLog.setAttemptTime(today);

        ProviderReminderCallLog callLogForAnotherProvider = createReminderCallLog("provider2");

        providerReminderCallLogRepository.save(olderCallLog);
        providerReminderCallLogRepository.save(newerCallLog);
        providerReminderCallLogRepository.save(callLogForAnotherProvider);

        List<ProviderReminderCallLog> actualCallLogs = providerReminderCallLogRepository.findByProviderIdAndAttemptTimeLessThan(providerId, new Timestamp(new DateTime().toDate().getTime()), new MostRecentProviderReminderCallLog());

        assertEquals(newerCallLog, actualCallLogs.get(0));
        assertEquals(1, actualCallLogs.size());
    }

    @After
    public void tearDown() {
        providerReminderCallLogRepository.deleteAll();
    }
}
