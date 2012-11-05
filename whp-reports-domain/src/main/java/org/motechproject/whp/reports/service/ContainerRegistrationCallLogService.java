package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.AllContainerRegistrationCallLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerRegistrationCallLogService {

    private AllContainerRegistrationCallLogs allContainerRegistrationCallLogs;

    @Autowired
    public ContainerRegistrationCallLogService(AllContainerRegistrationCallLogs allContainerRegistrationCallLogs) {
        this.allContainerRegistrationCallLogs = allContainerRegistrationCallLogs;
    }

    public void save(ContainerRegistrationCallLog containerRegistrationCallLog) {
        allContainerRegistrationCallLogs.save(containerRegistrationCallLog);
    }
}
