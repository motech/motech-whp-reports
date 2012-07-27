package org.motechproject.whp.reports.acceptance.calllog;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.repository.AllCallLogs;
import org.motechproject.whp.reports.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.joda.time.DateTime.now;
import static org.junit.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;

@Transactional
public class MaxDurationOfCallsTest extends IntegrationTest<CallLog> {

    @Autowired
    AllCallLogs allCallLogs;

    @Autowired
    DataAccessTemplate template;

    Session currentSession;

    @Before
    public void setup() {
        currentSession = template.getSessionFactory().getCurrentSession();
    }

    @Test
    public void shouldRetrieveMaxDurationOfCallsTillToday() {
        DateTime now = now();

        allCallLogs.save(purge(newCallLog().forProvider("provider").starting(now.minusDays(1)).forSeconds(10).build()));
        allCallLogs.save(purge(newCallLog().forProvider("provider").starting(now).forSeconds(20).build()));
        Query query = currentSession.createQuery("select max(durationInSeconds) from CallLog");
        assertEquals(20L, query.list().get(0));
    }
}
