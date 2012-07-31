package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.CallLog;
import org.motechproject.whp.reports.repository.AllCallLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    public void save(CallLog log) {
        allCallLogs.save(log);
    }
}
