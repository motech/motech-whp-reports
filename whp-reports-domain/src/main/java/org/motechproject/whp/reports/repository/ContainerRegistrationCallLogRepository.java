package org.motechproject.whp.reports.repository;

import org.motechproject.whp.reports.domain.measure.calllog.ContainerRegistrationCallLog;

public interface ContainerRegistrationCallLogRepository  extends MotechJpaRepository<ContainerRegistrationCallLog> {

    ContainerRegistrationCallLog findByCallId(String callId);
}
