package org.motechproject.whp.reports.acceptance.calllog;

import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.repository.AllCallLogs;
import org.motechproject.whp.reports.repository.DataAccessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;
import static org.motechproject.whp.reports.builder.DateTimeDimensionBuilder.newDateTimeDimension;

@Transactional
public class MaxDurationOfCallsTest extends IntegrationTest<Object> {

    @Autowired
    AllCallLogs allCallLogs;

    @Autowired
    DataAccessTemplate template;

    @Autowired
    PlatformTransactionManager manager;

    Session currentSession;

    @Before
    public void setup() {
        currentSession = template.getSessionFactory().getCurrentSession();
    }

    @Test
    public void shouldRetrieveMaxDurationOfCallsTillToday() {
        DateTimeDimension dimension1 = newDateTimeDimension().withDay(12).create(template, manager);
        DateTimeDimension dimension2 = newDateTimeDimension().withDay(14).create(template, manager);
        purge(dimension1, dimension2);

        allCallLogs.save((CallLog) purge(newCallLog().forProvider("provider").starting(dimension1).forSeconds(10).build()));
        allCallLogs.save((CallLog) purge(newCallLog().forProvider("provider").starting(dimension2).forSeconds(20).build()));
        Query query = currentSession.createQuery("select max(durationInSeconds) from CallLog");
        assertEquals(20L, query.list().get(0));
    }

}
