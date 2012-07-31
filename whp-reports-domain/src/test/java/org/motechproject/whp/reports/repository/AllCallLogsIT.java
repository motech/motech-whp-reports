package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.dimension.DateDimension;
import org.motechproject.whp.reports.domain.measure.CallLog;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;
import static org.motechproject.whp.reports.builder.DateDimensionBuilder.newDateTimeDimension;

public class AllCallLogsIT extends IntegrationTest<Object> {

    @Autowired
    DataAccessTemplate template;

    @Test
    public void shouldCreateCallLog() {
        CallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting((DateDimension) purge(newDateTimeDimension().create(template, null)))
                .build();

        saveOrUpdate(callLog);
        assertNotNull(callLog.getId());
    }

    @Test
    public void shouldUpdateCallLog() {
        CallLog callLog = newCallLog()
                .forProvider("providerId")
                .withNumber("provider")
                .starting((DateDimension) purge(newDateTimeDimension().create(template, null)))
                .build();

        saveOrUpdate(callLog);

        String cmfAdmin = "cmfAdmin";
        callLog.setCalledBy(cmfAdmin);
        saveOrUpdate(callLog);

        CallLog callLogFromDB = template.get(CallLog.class, callLog.getId());

        assertThat(callLogFromDB.getCalledBy(), is(cmfAdmin));
    }
}
