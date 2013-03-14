package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.calllog.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AdherenceCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdherenceCallLogService {

    private AdherenceCallLogRepository adherenceCallLogRepository;

    /*Required for spring proxy*/
    AdherenceCallLogService() {
    }

    @Autowired
    public AdherenceCallLogService(AdherenceCallLogRepository adherenceCallLogRepository) {
        this.adherenceCallLogRepository = adherenceCallLogRepository;
    }

    public void save(AdherenceCallLog log) {
        adherenceCallLogRepository.save(log);
    }
}
