package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;

public class AllCallLogsIT extends IntegrationTest<Object> {

    @Autowired
    AllCallLogs allCallLogs;

    @Test
    @Transactional
    public void shouldCreateCallLog() {
        AdherenceCallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting(new Date())
                .withTotalPatients(10)
                .withAdherenceCaptured(4)
                .withAdherenceNotCaptured(6)
                .withCallId("callIdlllllllllllllllllllllllllllllllllllllllllllllllllllllll")
                .withDuration(100)
                .build();

        allCallLogs.save(callLog);
        assertNotNull(callLog.getId());

        AdherenceCallLog callLogFromDB = allCallLogs.get(callLog.getId());
        assertThat(callLogFromDB, is(callLog));
    }

    @Test
    @Transactional
    public void shouldUpdateCallLog() {
        AdherenceCallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting(new Date())
                .build();

        allCallLogs.save(callLog);

        String cmfAdmin = "cmfAdmin";
        callLog.setCalledBy(cmfAdmin);
        allCallLogs.save(callLog);

        AdherenceCallLog callLogFromDB = allCallLogs.get(callLog.getId());

        assertThat(callLogFromDB.getCalledBy(), is(cmfAdmin));
    }
}
