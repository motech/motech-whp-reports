package org.motechproject.whp.reports.repository;

import org.junit.After;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.adherence.AdherenceAuditLog;
import org.motechproject.whp.reports.domain.dimension.Provider;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;


public class AdherenceAuditLogRepositoryIT extends IntegrationTest{

    @Autowired
    AdherenceAuditLogRepository adherenceAuditLogRepository;

    @Test
    public void shouldSaveAdherenceAuditLogs() {
        Provider provider = createProvider("provider1", "district");
        providerRepository.save(provider);

        AdherenceAuditLog adherenceAuditLog = new AdherenceAuditLog();
        long currentTime = System.currentTimeMillis();
        adherenceAuditLog.setCreationTime(new Timestamp(currentTime));
        adherenceAuditLog.setPatientId("patientId");
        adherenceAuditLog.setProviderId(provider.getProviderId());
        adherenceAuditLog.setUserId("userId");
        adherenceAuditLog.setDoseDate(new Date(currentTime));
        adherenceAuditLog.setNumberOfDosesTaken(2);
        adherenceAuditLog.setPillStatus("pillStatus");
        adherenceAuditLog.setChannel("channel");
        adherenceAuditLog.setTbId("tbId");
        adherenceAuditLog.setIsGivenByProvider("Y");

        adherenceAuditLogRepository.save(adherenceAuditLog);

        assertNotNull(adherenceAuditLog.getId());
    }

    @Override
    @After
    public void tearDown() {
        adherenceAuditLogRepository.deleteAll();
        providerRepository.deleteAll();
    }
}
