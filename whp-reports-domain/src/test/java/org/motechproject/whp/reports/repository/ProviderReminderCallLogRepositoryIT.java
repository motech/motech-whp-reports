package org.motechproject.whp.reports.repository;

import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.contract.enums.ReminderDisconnectionType;
import org.motechproject.whp.reports.contract.enums.ReminderType;
import org.motechproject.whp.reports.domain.measure.ProviderReminderCallLog;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProviderReminderCallLogRepositoryIT extends IntegrationTest {

    @Autowired
    private ProviderReminderCallLogRepository providerReminderCallLogRepository;

    @Test
    public void shouldCreateCallLog() {
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
        providerReminderCallLog.setProviderId("providerId");

        providerReminderCallLogRepository.save(providerReminderCallLog);

        assertNotNull(providerReminderCallLog.getId());
        assertEquals(providerReminderCallLog, providerReminderCallLogRepository.findOne(providerReminderCallLog.getId()));
    }

    @After
    public void tearDown() {
        providerReminderCallLogRepository.deleteAll();
    }
}
