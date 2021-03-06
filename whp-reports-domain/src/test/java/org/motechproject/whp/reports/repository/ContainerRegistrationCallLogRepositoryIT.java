package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.calllog.ContainerRegistrationCallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public class ContainerRegistrationCallLogRepositoryIT extends IntegrationTest {

    @Autowired
    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;

    @Test
    @Transactional
    public void shouldCreateCallLog() {
        ContainerRegistrationCallLog containerRegistrationCallLog = new ContainerRegistrationCallLog();

        containerRegistrationCallLog.setCallId(String.valueOf(System.currentTimeMillis()));
        containerRegistrationCallLog.setDisconnectionType("disconnectionType");
        containerRegistrationCallLog.setDuration(1000);
        containerRegistrationCallLog.setEndDateTime(new Timestamp(1000));
        containerRegistrationCallLog.setStartDateTime(new Timestamp(0));
        containerRegistrationCallLog.setProviderId("providerid");
        containerRegistrationCallLog.setMobileNumber("1234567890");

        assertNull(containerRegistrationCallLog.getId());
        containerRegistrationCallLogRepository.save(containerRegistrationCallLog);
        assertTrue(isNotBlank(containerRegistrationCallLog.getId().toString()));
        assertEquals(containerRegistrationCallLog, containerRegistrationCallLogRepository.findOne(containerRegistrationCallLog.getId()));
    }

    @Test
    @Transactional
    public void shouldFindCallLogByCallId() {
        String callId = String.valueOf(System.currentTimeMillis());
        ContainerRegistrationCallLog containerRegistrationCallLog = new ContainerRegistrationCallLog();
        containerRegistrationCallLog.setCallId(callId);
        containerRegistrationCallLogRepository.save(containerRegistrationCallLog);

        ContainerRegistrationCallLog actualCallLog = containerRegistrationCallLogRepository.findByCallId(callId);

        assertNotNull(actualCallLog);
        assertEquals(callId, actualCallLog.getCallId());
    }
}
