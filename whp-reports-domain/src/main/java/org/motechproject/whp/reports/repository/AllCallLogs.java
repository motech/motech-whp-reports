package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.CallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllCallLogs {

    @Autowired
    private DataAccessTemplate template;

    public void save(CallLog callLog) {
        template.saveOrUpdate(callLog);
    }
}
