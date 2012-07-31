package org.motechproject.whp.reports.acceptance.calllog;

import org.junit.Before;
import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.dataservice.DateTimeDimensionService;
import org.motechproject.whp.reports.domain.dimension.DateTimeDimension;
import org.motechproject.whp.reports.service.CallLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.motechproject.whp.reports.builder.CallLogBuilder.newCallLog;
import static org.motechproject.whp.reports.builder.DateTimeDimensionBuilder.newDateTimeDimension;

public class MaxDurationOfCallsTest extends IntegrationTest<Object> {

    @Autowired
    CallLogService logService;

    @Autowired
    DateTimeDimensionService dateTimeDimensionService;

    List<DateTimeDimension> dimensions = null;

    @Before
    public void setup() {
        setupDateDimension();
        setupLogs();
    }

    private void setupDateDimension() {
        dimensions = dateTimeDimensionService.save(newDateTimeDimension().withDay(12).build(), newDateTimeDimension().withDay(14).build());
    }

    private void setupLogs() {
        logService.save(newCallLog().forProvider("provider").starting(dimensions.get(0)).forSeconds(10).build());
        logService.save(newCallLog().forProvider("provider").starting(dimensions.get(1)).forSeconds(20).build());
    }

    @Test
    @Transactional
    public void shouldRetrieveMaxDurationOfCallsTillToday() {
        assertEquals(20L, fetch("select max(durationInSeconds) from CallLog").get(0));
    }
}
