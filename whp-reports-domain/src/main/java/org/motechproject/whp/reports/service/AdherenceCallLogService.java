package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AllAdherenceCallLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdherenceCallLogService {

    private AllAdherenceCallLogs allCallLogs;

    /*Required for spring proxy*/
    AdherenceCallLogService() {
    }

    @Autowired
    public AdherenceCallLogService(AllAdherenceCallLogs allCallLogs) {
        this.allCallLogs = allCallLogs;
    }

    public void save(AdherenceCallLog log) {
        allCallLogs.save(log);
    }
}
