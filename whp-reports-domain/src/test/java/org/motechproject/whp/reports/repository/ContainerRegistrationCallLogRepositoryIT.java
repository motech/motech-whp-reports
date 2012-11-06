package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.UUID;

import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.junit.Assert.*;

public class ContainerRegistrationCallLogRepositoryIT extends IntegrationTest<ContainerRegistrationCallLog> {

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
}
