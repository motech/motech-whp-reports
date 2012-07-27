package org.motechproject.whp.reports.repository;

import org.joda.time.DateTime;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;

public class AllCallLogsIT extends IntegrationTest<CallLog> {

    @Autowired
    AllCallLogs allCallLogs;

    @Test
    public void shouldCreateCallLog() {
        CallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting(new DateTime())
                .forSeconds(20)
                .build();

        allCallLogs.save(purge(callLog));
        assertNotNull(callLog.getId());
    }

    @Test
    public void shouldUpdateCallLog() {
        CallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting(new DateTime())
                .forSeconds(20)
                .build();

        allCallLogs.save(purge(callLog));

        String cmfAdmin = "cmfAdmin";
        callLog.setCalledBy(cmfAdmin);
        allCallLogs.save(callLog);

        CallLog callLogFromDB = template.get(CallLog.class, callLog.getId());

        assertThat(callLogFromDB.getCalledBy(), is(cmfAdmin));
    }
}
