package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContainerRegistrationCallLogService {

    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;

    @Autowired
    public ContainerRegistrationCallLogService(ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository) {
        this.containerRegistrationCallLogRepository = containerRegistrationCallLogRepository;
    }

    public void save(ContainerRegistrationCallLog containerRegistrationCallLog) {
        containerRegistrationCallLogRepository.save(containerRegistrationCallLog);
    }
}
