package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.AdherenceCallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllAdherenceCallLogs {

    @Autowired
    private DataAccessTemplate template;

    public void save(AdherenceCallLog callLog) {
        template.saveOrUpdate(callLog);
    }

    public AdherenceCallLog get(long callLogId) {
        return template.get(AdherenceCallLog.class, callLogId);
    }
}
