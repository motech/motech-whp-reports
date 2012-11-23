package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.ContainerRegistrationCallLog;
import org.springframework.data.rest.repository.annotation.RestResource;

public interface ContainerRegistrationCallLogRepository  extends MotechJpaRepository<ContainerRegistrationCallLog> {

    ContainerRegistrationCallLog findByCallId(String callId);
}
