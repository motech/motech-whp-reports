package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AllContainerRegistrationCallLogs {

    @Autowired
    private DataAccessTemplate template;

    public void save(ContainerRegistrationCallLog callLog) {
        template.saveOrUpdate(callLog);
    }

    public ContainerRegistrationCallLog get(long callLogId) {
        return template.get(ContainerRegistrationCallLog.class, callLogId);
    }
}
