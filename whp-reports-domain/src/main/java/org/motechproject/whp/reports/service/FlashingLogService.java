package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.repository.AllFlashingLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlashingLogService {

    private AllFlashingLogs allFlashingLogs;

    /*Required for spring proxy*/
    FlashingLogService() {
    }

    @Autowired
    public FlashingLogService(AllFlashingLogs allFlashingLogs) {
        this.allFlashingLogs = allFlashingLogs;
    }

    public void save(FlashingLog log) {
        allFlashingLogs.save(log);
    }
}
