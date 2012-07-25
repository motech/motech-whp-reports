package org.motechproject.whp.reports.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.CallLog;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AllCallLogsIT extends IntegrationTest {

    @Autowired
    AllCallLogs allCallLogs;

    @Test
    public void shouldCreateCallLog() {
        final CallLog callLog = new CallLog();
        callLog.setCalledBy("provider");
        callLog.setEndTime(new DateTime().plusMinutes(20).toDate());
        callLog.setStartTime(new DateTime().toDate());
        callLog.setProviderId("providerId");

        allCallLogs.save(callLog);

        assertNotNull(callLog.getId());
    }

    @Test
    public void shouldUpdateCallLog() {
        final CallLog callLog = new CallLog();
        callLog.setCalledBy("provider");
        callLog.setEndTime(new DateTime().plusMinutes(20).toDate());
        callLog.setStartTime(new DateTime().toDate());
        callLog.setProviderId("providerId");

        allCallLogs.save(callLog);

        String cmfAdmin = "cmfAdmin";
        callLog.setCalledBy(cmfAdmin);
        allCallLogs.save(callLog);

        CallLog callLogFromDB = template.get(CallLog.class, callLog.getId());

        assertThat(callLogFromDB.getCalledBy(), is(cmfAdmin));
    }
}
