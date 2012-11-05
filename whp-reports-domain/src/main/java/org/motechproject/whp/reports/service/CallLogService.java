package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.motechproject.whp.reports.repository.AllCallLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CallLogService {

    private AllCallLogs allCallLogs;

    /*Required for spring proxy*/
    CallLogService() {
    }

    @Autowired
    public CallLogService(AllCallLogs allCallLogs) {
        this.allCallLogs = allCallLogs;
    }

    public void save(AdherenceCallLog log) {
        allCallLogs.save(log);
    }
}
