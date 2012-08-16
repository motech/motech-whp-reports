package org.motechproject.whp.reports.repository;

import org.junit.Test;
import org.motechproject.whp.reports.IntegrationTest;
import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.motechproject.whp.reports.builder.FlashingLogBuilder.newFlashingLog;

public class AllFlashingLogsIT extends IntegrationTest<Object> {

    @Autowired
    AllFlashingLogs allFlashingLogs;

    @Test
    @Transactional
    public void shouldCreateFlashingLog() {
        FlashingLog flashingLog = newFlashingLog()
                .forProvider("providerId")
                .withMobileNumber("1234567890")
                .withCallTime(new Date())
                .build();

        allFlashingLogs.save(flashingLog);
        assertNotNull(flashingLog.getId());

        FlashingLog fetchedFlashingLog = allFlashingLogs.get(flashingLog.getId());
        assertThat(fetchedFlashingLog, is(flashingLog));
    }

    @Test
    @Transactional
    public void shouldUpdateFlashingLog() {
        FlashingLog flashingLog = newFlashingLog()
                .forProvider("providerId")
                .withCallTime(new Date())
                .build();

        allFlashingLogs.save(flashingLog);

        String mobileNumber = "1234567890";
        flashingLog.setMobileNumber(mobileNumber);
        allFlashingLogs.save(flashingLog);

        FlashingLog fetchedFlashingLog = allFlashingLogs.get(flashingLog.getId());

        assertThat(fetchedFlashingLog.getMobileNumber(), is(mobileNumber));
    }
}
