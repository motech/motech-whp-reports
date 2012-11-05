package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.FlashingLog;
import org.motechproject.whp.reports.repository.FlashingLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlashingLogService {

    private FlashingLogRepository flashingLogRepository;

    /*Required for spring proxy*/
    FlashingLogService() {
    }

    @Autowired
    public FlashingLogService(FlashingLogRepository flashingLogRepository) {
        this.flashingLogRepository = flashingLogRepository;
    }

    public void save(FlashingLog log) {
        flashingLogRepository.save(log);
    }
}
