package org.motechproject.whp.reports.service;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.motechproject.whp.reports.repository.ContainerRegistrationCallLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContainerRegistrationCallLogService {

    private ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository;

    public ContainerRegistrationCallLogService() {
    }

    @Autowired
    public ContainerRegistrationCallLogService(ContainerRegistrationCallLogRepository containerRegistrationCallLogRepository) {
        this.containerRegistrationCallLogRepository = containerRegistrationCallLogRepository;
    }

    public void save(ContainerRegistrationCallLog containerRegistrationCallLog) {
        containerRegistrationCallLogRepository.save(containerRegistrationCallLog);
    }
}
