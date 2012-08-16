package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllFlashingLogs {

    @Autowired
    private DataAccessTemplate template;

    public void save(FlashingLog flashingLog) {
        template.saveOrUpdate(flashingLog);
    }

    public FlashingLog get(long flashingLogId) {
        return template.get(FlashingLog.class, flashingLogId);
    }
}
